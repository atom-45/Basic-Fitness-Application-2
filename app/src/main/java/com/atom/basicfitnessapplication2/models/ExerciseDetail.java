package com.atom.basicfitnessapplication2.models;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ExerciseDetail {

    private final String exerciseName;
    private final String exerciseFocus;
    private final int exerciseImageID;

    public ExerciseDetail(String exerciseName, String exerciseFocus, int exerciseImageID) {
        this.exerciseName = exerciseName;
        this.exerciseFocus = exerciseFocus;
        this.exerciseImageID = exerciseImageID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseFocus() {
        return exerciseFocus;
    }

    public int getExerciseImageID() {
        return exerciseImageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseDetail)) return false;
        ExerciseDetail that = (ExerciseDetail) o;
        return getExerciseImageID() == that.getExerciseImageID() &&
                getExerciseName().equals(that.getExerciseName()) &&
                getExerciseFocus().equals(that.getExerciseFocus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExerciseName(), getExerciseFocus(), getExerciseImageID());
    }

    @NonNull
    @Override
    public String toString() {
        return "ExerciseDetail{" +
                "exerciseName='" + exerciseName + '\'' +
                ", exerciseFocus='" + exerciseFocus + '\'' +
                ", exerciseImageID=" + exerciseImageID +
                '}';
    }
}
