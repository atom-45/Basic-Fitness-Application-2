package com.atom.basicfitnessapplication2.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.PropertyName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CurrentExerciseMode {

    private final String start_time;
    private final String date;
    private final String exercise_type;

    public CurrentExerciseMode(String startTime, String date, String exerciseType)
    {
        this.start_time = startTime;
        this.date = date;
        this.exercise_type = exerciseType;
    }

    @PropertyName("start_time")
    public String getStartTime() {
        return start_time;
    }

    @PropertyName("date")
    public String getDate() {
        return date;
    }

    @PropertyName("exercise_type")
    public String getExerciseType() {
        return exercise_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentExerciseMode)) return false;
        CurrentExerciseMode that = (CurrentExerciseMode) o;
        return getStartTime().equals(that.getStartTime()) &&
                getDate().equals(that.getDate()) &&
                getExerciseType().equals(that.getExerciseType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getDate(), getExerciseType());
    }

    @NonNull
    @Override
    public String toString() {
        return "CurrentExercise{" +
                "startTime='" + start_time + '\'' +
                ", date='" + date + '\'' +
                ", exerciseType='" + exercise_type + '\'' +
                '}';
    }

    public Map<String, String> toMap(){
        Map<String, String> map = new HashMap<>();
        map.put("start_time",start_time);
        map.put("date",date);
        map.put("exercise_type",exercise_type);

        return map;
    }
}
