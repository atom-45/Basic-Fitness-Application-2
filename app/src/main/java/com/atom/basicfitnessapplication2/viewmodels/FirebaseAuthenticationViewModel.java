package com.atom.basicfitnessapplication2.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.atom.basicfitnessapplication2.applications.MyApplication;
import com.atom.basicfitnessapplication2.repositories.FirebaseAuthenticationRepository;

import javax.inject.Inject;

public class FirebaseAuthenticationViewModel extends AndroidViewModel {

    private final FirebaseAuthenticationRepository firebaseAuthenticationRepository;
    private static final String TAG = "FirebaseAuthRepository";

    @Inject
    public FirebaseAuthenticationViewModel(@NonNull Application application,
                                           FirebaseAuthenticationRepository authenticationRepository) {
        super(application);
        this.firebaseAuthenticationRepository = authenticationRepository;
    }

    public static final ViewModelInitializer<FirebaseAuthenticationViewModel> initializer =
            new ViewModelInitializer<>(FirebaseAuthenticationViewModel.class, creationExtras -> {

                MyApplication application = new MyApplication();
                return new FirebaseAuthenticationViewModel(application,
                        application.applicationComponent.firebaseAuthenticationRepository());

            });

    public void signInUseWithEmailAndPassword(){
        firebaseAuthenticationRepository.signInUseWithEmailAndPassword()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: user successfully signed in ");
                    } else {
                        Log.e(TAG, "onComplete: user failed to sign in",task.getException());
                    }
                }).addOnFailureListener(e -> Log.e(TAG, "onFailure: failed user sign in",e));
    }
}
