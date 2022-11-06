package com.atom.basicfitnessapplication2.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.atom.basicfitnessapplication2.applications.MyApplication;
import com.atom.basicfitnessapplication2.repositories.FirebaseRTDBRepository;
import com.google.firebase.database.DatabaseReference;

import java.util.Map;

import javax.inject.Inject;

public class FirebaseRTDBViewModel extends AndroidViewModel {

    private final FirebaseRTDBRepository firebaseRTDBRepository;
    private static final String TAG = "FirebaseRTDBViewModel";

    @Inject
    public FirebaseRTDBViewModel(@NonNull Application application,
                                 FirebaseRTDBRepository rtdbRepository) {
        super(application);
        this.firebaseRTDBRepository = rtdbRepository;
    }

    public static final ViewModelInitializer<FirebaseRTDBViewModel> initializer =
            new ViewModelInitializer<>(FirebaseRTDBViewModel.class, creationExtras -> {
                MyApplication application = new MyApplication();

                return new FirebaseRTDBViewModel(application,
                        application.applicationComponent.firebaseRTDBRepository());
            } );

    public void saveSelectedExerciseMode(Context context, Map<String, Object> currentExerciseObject){
        firebaseRTDBRepository.saveSelectedExerciseMode(currentExerciseObject)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: Exercise mode saved on the database");
                        Toast.makeText(context, "Exercise mode set", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e(TAG, "onComplete: task exception - ",task.getException());
                    }
                }).addOnFailureListener(e -> Log.e(TAG, "onFailure:",e));
    }

    public DatabaseReference getSpecificExerciseData(String exerciseType){
        return firebaseRTDBRepository.getSpecificExerciseDatabaseReference(exerciseType);
    }
}
