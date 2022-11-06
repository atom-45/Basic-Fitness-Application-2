package com.atom.basicfitnessapplication2.repositories;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.Map;

import javax.inject.Inject;

public class FirebaseRTDBRepository {

    private final DatabaseReference databaseReference;

    @Inject
    public FirebaseRTDBRepository(DatabaseReference databaseReference){
        this.databaseReference = databaseReference;
    }

    public Task<Void> saveSelectedExerciseMode(Map<String, Object> currentExerciseObject){
        return databaseReference.child("/").updateChildren(currentExerciseObject);
    }

    public DatabaseReference getSpecificExerciseDatabaseReference(String exerciseType){
        return databaseReference.child(exerciseType);
    }
}
