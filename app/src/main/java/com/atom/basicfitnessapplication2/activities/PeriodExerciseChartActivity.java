package com.atom.basicfitnessapplication2.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.atom.basicfitnessapplication.databinding.ActivityPeriodExerciseChartBinding;
import com.atom.basicfitnessapplication2.models.Constants;
import com.atom.basicfitnessapplication2.models.Exercise;
import com.atom.basicfitnessapplication2.viewmodels.FirebaseRTDBViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**This class will not be completed, or will be completed in a future date*/
public class PeriodExerciseChartActivity extends AppCompatActivity {

    private static final String TAG = "PeriodExerciseChartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPeriodExerciseChartBinding periodExerciseChartBinding =
                ActivityPeriodExerciseChartBinding.inflate(getLayoutInflater());

        View view = periodExerciseChartBinding.getRoot();
        setContentView(view);

        FirebaseRTDBViewModel rtdbViewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory
                        .from(FirebaseRTDBViewModel.initializer))
                .get(FirebaseRTDBViewModel.class);

        String exerciseType = getIntent().getStringExtra(Constants.EXERCISE_TYPE);
        String dateOne = getIntent().getStringExtra(Constants.DATE_SELECTION_ONE);
        String dateTwo = getIntent().getStringExtra(Constants.DATE_SELECTION_TWO);

        rtdbViewModel.getSpecificExerciseData(exerciseType).addValueEventListener(
                new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<Map<String, Exercise>> exerciseMapGenericTypeIndicator
                        = new GenericTypeIndicator<Map<String, Exercise>>() {};

                if(snapshot.exists()) {
                    Map<String, Exercise> exerciseMap =
                            snapshot.getValue(exerciseMapGenericTypeIndicator);

                    assert exerciseMap != null;
                    List<Exercise> exercisesDateOne = exerciseMap.values()
                            .stream()
                            .filter(exercise -> exercise.getDate().equals(dateOne))
                            .collect(Collectors.toList());

                    List<Exercise> exercisesDateTwo = exerciseMap.values()
                            .stream()
                            .filter(exercise -> exercise.getDate().equals(dateTwo))
                            .collect(Collectors.toList());

                    Map<String, List<Exercise>> periodExercisesDates  = new HashMap<>();

                    /**List<String> dates = new ArrayList<>();
                    dates.add("2022-10-20");
                    dates.add("2022-11-01");

                    for(String date: dates){
                        periodExercisesDates.put(date, exerciseMap.values()
                                .stream()
                                .filter(exercise -> exercise.getDate().equals(date))
                                .collect(Collectors.toList()));
                    }**/

                }  else {
                    Log.d(TAG, "onDataChange: unable to" +
                            " retrieve data snapshot of the particular exercise");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}