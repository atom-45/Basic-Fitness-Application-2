package com.atom.basicfitnessapplication2.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.atom.basicfitnessapplication.databinding.ActivityDailyExerciseChartBinding;
import com.atom.basicfitnessapplication2.models.Constants;
import com.atom.basicfitnessapplication2.models.Exercise;
import com.atom.basicfitnessapplication2.viewmodels.FirebaseRTDBViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class DailyExerciseChartActivity extends AppCompatActivity {

    private static final String TAG = "DailyExerciseChartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDailyExerciseChartBinding dailyExerciseChartBinding =
                ActivityDailyExerciseChartBinding.inflate(getLayoutInflater());
        View view = dailyExerciseChartBinding.getRoot();
        setContentView(view);

        FirebaseRTDBViewModel rtdbViewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory.from(FirebaseRTDBViewModel.initializer))
                .get(FirebaseRTDBViewModel.class);

        String date = getIntent().getStringExtra(Constants.CURRENT_DATE);
        String exerciseType = getIntent().getStringExtra(Constants.EXERCISE_TYPE);


        dailyExerciseChartBinding.parameterSelectionTextView1.setAdapter(
                new ArrayAdapter<>(getApplicationContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                        Constants.parameterChoice()));

        rtdbViewModel.getSpecificExerciseData(exerciseType)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<Map<String, Exercise>> exerciseMapGenericTypeIndicator
                        = new GenericTypeIndicator<Map<String, Exercise>>() {};

                if(snapshot.exists()){
                    //We will assume that the exerciseMap is exactly the same data in the same order that is extracted from the database.
                    Map<String, Exercise> exerciseMap =
                            snapshot.getValue(exerciseMapGenericTypeIndicator);

                    List<Integer> values = new ArrayList<>();
                    List<Entry> entries = new ArrayList<>();


                    assert exerciseMap != null;
                    List<Exercise> exercises = new ArrayList<>(exerciseMap.values())
                            .stream()
                            .filter(exercise -> exercise.getDate().equals(date))
                            .collect(Collectors.toList());

                    List<Double> accelerationXValues = exercises.stream()
                            .map(Exercise::getAcceleration_x)
                            .collect(Collectors.toList());

                    List<Double> accelerationYValues = exercises.stream()
                            .map(Exercise::getAcceleration_y)
                            .collect(Collectors.toList());

                    List<Double> accelerationZValues = exercises.stream()
                            .map(Exercise::getAcceleration_z)
                            .collect(Collectors.toList());

                    List<Double> gyroXValues = exercises.stream()
                            .map(Exercise::getGyro_x)
                            .collect(Collectors.toList());

                    List<Double> gyroYValues = exercises.stream()
                            .map(Exercise::getGyro_y)
                            .collect(Collectors.toList());

                    List<Double> gyroZValues = exercises.stream()
                            .map(Exercise::getGyro_z)
                            .collect(Collectors.toList());

                    Log.d(TAG, "onDataChange acceleration X: "+accelerationXValues);
                    Log.d(TAG, "onDataChange acceleration Y: "+accelerationYValues);
                    Log.d(TAG, "onDataChange acceleration Z: "+accelerationZValues);

                    for (int i = 0; i <exercises.size() ; i++) {
                        values.add(i);
                    }


                    dailyExerciseChartBinding.netAccelerationXTextView1b.setText(String.format(
                            Locale.ENGLISH, "%.2f m/s^2",
                            calculateDoubleAverage(accelerationXValues)));

                    dailyExerciseChartBinding.netAccelerationYTextView2b.setText(String.format(
                            Locale.ENGLISH, "%.2f m/s^2",
                            calculateDoubleAverage(accelerationYValues)));

                    dailyExerciseChartBinding.netAccelerationZTextView3b.setText(String.format(
                            Locale.ENGLISH, "%.2f m/s^2",
                            calculateDoubleAverage(accelerationZValues)));

                    dailyExerciseChartBinding.netGyroXTextView4b.setText(String.format(
                            Locale.ENGLISH, "%.2f rad/s",
                            calculateDoubleAverage(gyroXValues)));

                    dailyExerciseChartBinding.netGyroYTextView5b.setText(String.format(
                            Locale.ENGLISH, "%.2f rad/s ",
                            calculateDoubleAverage(gyroYValues)));

                    dailyExerciseChartBinding.netGyroZTextView6b.setText(String.format(
                            Locale.ENGLISH, "%.2f rad/s",
                            calculateDoubleAverage(gyroZValues)));

                   dailyExerciseChartBinding.dailyDelayTimeTextView1.setText(exercises.get(0).getDelay_time());
                   dailyExerciseChartBinding.startTimeTextView1.setText(exercises.get(0).getStart_time());

                   LineChart lineChart = dailyExerciseChartBinding.dailyLineChart;

                   dailyExerciseChartBinding.selectionButton1.setOnClickListener(view1 -> {

                       String selection = dailyExerciseChartBinding
                               .parameterSelectionTextView1.getText().toString();
                       LineDataSet lineDataSet;
                       LineData lineData = null;

                       switch (selection) {
                           case "Acceleration X": {
                               for (int i = 0; i < values.size(); i++) {
                                   entries.add(new Entry(values.get(i),
                                           accelerationXValues.get(i).floatValue()));
                               }
                               lineDataSet = new LineDataSet(entries, "Acceleration in the X direction");
                               lineDataSet.setColor(Color.parseColor("#423465"));
                               lineDataSet.setFormSize(15f);

                               lineData = new LineData(lineDataSet);

                               break;
                           }

                           case "Acceleration Y": {
                               for (int i = 0; i < values.size(); i++) {
                                   entries.add(new Entry(values.get(i),
                                           accelerationYValues.get(i).floatValue()));
                               }

                               lineDataSet = new LineDataSet(entries, "Acceleration in the Y direction");
                               lineDataSet.setColor(Color.parseColor("#423465"));
                               lineDataSet.setFormSize(15f);

                               lineData = new LineData(lineDataSet);

                               break;
                           }

                           case "Acceleration Z": {
                               for (int i = 0; i < values.size(); i++) {
                                   entries.add(new Entry(values.get(i),
                                           accelerationZValues.get(i).floatValue()));
                               }

                               lineDataSet = new LineDataSet(entries, "Acceleration in the Z direction");
                               lineDataSet.setColor(Color.parseColor("#423465"));
                               lineDataSet.setFormSize(15f);

                               lineData = new LineData(lineDataSet);

                               break;
                           }

                           case "Gyro X": {
                               for (int i = 0; i < values.size(); i++) {
                                   entries.add(new Entry(values.get(i),
                                           gyroXValues.get(i).floatValue()));
                               }

                               lineDataSet = new LineDataSet(entries, "Gyro in the X direction");
                               lineDataSet.setColor(Color.parseColor("#423465"));
                               lineDataSet.setFormSize(15f);

                               lineData = new LineData(lineDataSet);

                               break;
                           }

                           case "Gyro Y": {
                               for (int i = 0; i < values.size(); i++) {
                                   entries.add(new Entry(values.get(i),
                                           gyroYValues.get(i).floatValue()));
                               }

                               lineDataSet = new LineDataSet(entries, "Gyro in the Y direction");
                               lineDataSet.setColor(Color.parseColor("#423465"));
                               lineDataSet.setFormSize(15f);

                               lineData = new LineData(lineDataSet);

                               break;
                           }

                           case "Gyro Z": {
                               for (int i = 0; i < values.size(); i++) {
                                   entries.add(new Entry(values.get(i), gyroZValues.get(i).floatValue()));
                               }

                               lineDataSet = new LineDataSet(entries, "Gyro in the Z direction");
                               lineDataSet.setColor(Color.parseColor("#423465"));
                               lineDataSet.setFormSize(15f);

                               lineData = new LineData(lineDataSet);

                               break;
                           }
                           default:
                               Toast.makeText(DailyExerciseChartActivity.this,
                                       "This parameter does not exist", Toast.LENGTH_SHORT).show();
                               break;
                       }

                       Description description = new Description();
                       description.setEnabled(false);

                       XAxis xAxis = lineChart.getXAxis();
                       xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                       xAxis.setDrawAxisLine(false);
                       xAxis.setDrawGridLines(false);

                       YAxis yLeftAxis = lineChart.getAxisLeft();
                       yLeftAxis.setDrawAxisLine(false);

                       lineChart.setData(lineData);
                       lineChart.setDrawGridBackground(false);
                       lineChart.setDescription(description);
                       lineChart.animateY(1000);
                       lineChart.notifyDataSetChanged();
                       lineChart.invalidate();
                   });

                    Log.d(TAG, "onDataChange map values: "+exerciseMap.keySet());

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
    }

    private double calculateDoubleAverage(@NonNull List<Double> doubles){
        double d = doubles.stream().collect(Collectors.averagingDouble(Double::doubleValue));
        Log.d(TAG, "calculateAverage: "+d);

        return d;
    }
}