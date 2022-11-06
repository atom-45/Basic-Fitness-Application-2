package com.atom.basicfitnessapplication2.activities;

import android.os.Bundle;
import android.widget.PopupMenu;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.atom.basicfitnessapplication.R;
import com.atom.basicfitnessapplication.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_host_container);

        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.bottom_navigation_menu);

        activityMainBinding.smoothBottomBar
                .setupWithNavController(popupMenu.getMenu(),navController);
    }
}