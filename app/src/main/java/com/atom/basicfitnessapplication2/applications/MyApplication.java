package com.atom.basicfitnessapplication2.applications;

import android.app.Application;

import com.atom.basicfitnessapplication2.components.ApplicationComponent;
import com.atom.basicfitnessapplication2.components.DaggerApplicationComponent;


public class MyApplication extends Application {

    public ApplicationComponent applicationComponent = DaggerApplicationComponent.create();
}
