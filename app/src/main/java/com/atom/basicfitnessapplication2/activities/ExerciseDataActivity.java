package com.atom.basicfitnessapplication2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.atom.basicfitnessapplication.databinding.ActivityExerciseDataBinding;
import com.atom.basicfitnessapplication2.models.Constants;
import com.atom.basicfitnessapplication2.models.Exercise;
import com.atom.basicfitnessapplication2.viewmodels.FirebaseRTDBViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExerciseDataActivity extends AppCompatActivity {


    private static final String TAG = "ExerciseDataActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityExerciseDataBinding exerciseDataBinding =
                ActivityExerciseDataBinding.inflate(getLayoutInflater());
        View view = exerciseDataBinding.getRoot();
        setContentView(view);


        FirebaseRTDBViewModel rtdbViewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory.
                        from(FirebaseRTDBViewModel.initializer)).
                get(FirebaseRTDBViewModel.class);

        String exerciseType = getIntent()
                .getStringExtra(Constants.EXERCISE_TYPE)
                .toLowerCase()
                .replace(" ","_");

        rtdbViewModel.getSpecificExerciseData(exerciseType)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<Map<String, Exercise>> exerciseMapGenericTypeIndicator
                        = new GenericTypeIndicator<Map<String, Exercise>>() {};

                if(snapshot.exists()){
                    Map<String, Exercise> exerciseMap =
                            snapshot.getValue(exerciseMapGenericTypeIndicator);

                    assert exerciseMap != null;

                    //The map function below is used to access
                    List<String> exerciseDates = exerciseMap.values()
                            .stream()
                            .map(Exercise::getDate)
                            .distinct()
                            .collect(Collectors.toList());

                    Log.d(TAG, "onDataChange: "+exerciseMap);
                    Log.d(TAG, "onDataChange exercise dates: "+exerciseDates);

                    ArrayAdapter<String> datesAdapter = new ArrayAdapter<>(getApplicationContext(),
                            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                            exerciseDates);

                    exerciseDataBinding.currentDateTextView2b.setAdapter(datesAdapter);
                    exerciseDataBinding.dateSelectionOneTextView.setAdapter(datesAdapter);
                    exerciseDataBinding.dateSelectionTwoTextView.setAdapter(datesAdapter);

                } else {
                    Log.d(TAG, "onDataChange: unable to" +
                            " retrieve data snapshot of the particular exercise");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled exercise data:",error.toException());
            }
        });

        exerciseDataBinding.dailyShowButton.setOnClickListener(view1 -> {
            String date = exerciseDataBinding.currentDateTextView2b.getText().toString();
            if(!date.equals("")){
                startActivity(new Intent(view1.getContext(), DailyExerciseChartActivity.class)
                        .putExtra(Constants.CURRENT_DATE,date)
                        .putExtra(Constants.EXERCISE_TYPE, exerciseType));
            } else {
                Toast.makeText(this, "Date not selected. Please select date",
                        Toast.LENGTH_SHORT).show();
            }
        });

        exerciseDataBinding.periodShowButton.setOnClickListener(view12 -> {
            String dateOne = exerciseDataBinding.dateSelectionOneTextView.getText().toString();
            String dateTwo = exerciseDataBinding.dateSelectionTwoTextView.getText().toString();

            if (dateOne.equals("") || dateTwo.equals("")) {
                Toast.makeText(this, "Please ensure you have selected or entered both dates",
                        Toast.LENGTH_SHORT).show();
            } else if(dateOne.equals(dateTwo)){
                Toast.makeText(this, "Select two different dates", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(view12.getContext(), PeriodExerciseChartActivity.class)
                        .putExtra(Constants.DATE_SELECTION_ONE, dateOne)
                        .putExtra(Constants.DATE_SELECTION_TWO, dateTwo)
                        .putExtra(Constants.EXERCISE_TYPE, exerciseType));
            }
        });
    }
}