package com.atom.basicfitnessapplication2.components;

import com.atom.basicfitnessapplication2.modules.FirebaseModule;
import com.atom.basicfitnessapplication2.repositories.FirebaseAuthenticationRepository;
import com.atom.basicfitnessapplication2.repositories.FirebaseRTDBRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FirebaseModule.class})
public interface ApplicationComponent {

    FirebaseRTDBRepository firebaseRTDBRepository();
    FirebaseAuthenticationRepository firebaseAuthenticationRepository();


}
