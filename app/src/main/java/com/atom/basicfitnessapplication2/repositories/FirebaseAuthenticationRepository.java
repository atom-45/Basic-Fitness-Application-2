package com.atom.basicfitnessapplication2.repositories;


import com.atom.basicfitnessapplication2.models.Constants;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirebaseAuthenticationRepository {

    private final FirebaseAuth firebaseAuth;

    @Inject
    public FirebaseAuthenticationRepository(FirebaseAuth firebaseAuth){
        this.firebaseAuth = firebaseAuth;
    }

    public Task<AuthResult> signInUseWithEmailAndPassword(){
        return firebaseAuth.signInWithEmailAndPassword(Constants.EMAIL_ADDRESS,Constants.PASSWORD);
    }
}
