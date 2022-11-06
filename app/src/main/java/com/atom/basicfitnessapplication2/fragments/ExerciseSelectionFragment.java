package com.atom.basicfitnessapplication2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.atom.basicfitnessapplication.R;
import com.atom.basicfitnessapplication2.models.CurrentExerciseMode;
import com.atom.basicfitnessapplication2.viewmodels.FirebaseAuthenticationViewModel;
import com.atom.basicfitnessapplication2.viewmodels.FirebaseRTDBViewModel;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseSelectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CurrentExerciseMode currentExerciseMode;
    private Map<String, Object> exerciseTypeObject;

    public ExerciseSelectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExerciseSelectionFragment newInstance(String param1, String param2) {
        ExerciseSelectionFragment fragment = new ExerciseSelectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_selection, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseRTDBViewModel rtdbViewModel = new ViewModelProvider(this,
                ViewModelProvider.Factory.
                        from(FirebaseRTDBViewModel.initializer)).
                get(FirebaseRTDBViewModel.class);

        LocalDateTime localDateTime = LocalDateTime.now();

        view.findViewById(R.id.pushUpCardView).setOnClickListener(view1 -> {
            Toast.makeText(view.getContext(),
                    "Push Up exercises started.", Toast.LENGTH_SHORT).show();

            exerciseTypeObject = new HashMap<>();
            currentExerciseMode = new CurrentExerciseMode(
                    localDateTime.toLocalTime().toString(),
                    localDateTime.toLocalDate().toString(),
                    "pushUps");
            exerciseTypeObject.put("current", currentExerciseMode);

            rtdbViewModel.saveSelectedExerciseMode(view.getContext(), exerciseTypeObject);

        });

        view.findViewById(R.id.sitUpCardView).setOnClickListener(view12 -> {
            Toast.makeText(view.getContext(),
                    "Sit Up exercises started.", Toast.LENGTH_SHORT).show();

            exerciseTypeObject = new HashMap<>();
            currentExerciseMode = new CurrentExerciseMode(
                    localDateTime.toLocalTime().toString(),
                    localDateTime.toLocalDate().toString(),
                    "sitUps");
            exerciseTypeObject.put("current", currentExerciseMode);

            rtdbViewModel.saveSelectedExerciseMode(view.getContext(), exerciseTypeObject);

        });

        view.findViewById(R.id.mountainClimbersCardView).setOnClickListener(view13 -> {
            Toast.makeText(view.getContext(),
                    "Mountain climber exercises started.", Toast.LENGTH_SHORT).show();

            exerciseTypeObject = new HashMap<>();
            currentExerciseMode = new CurrentExerciseMode(
                    localDateTime.toLocalTime().toString(),
                    localDateTime.toLocalDate().toString(),
                    "mountainClimbers");
            exerciseTypeObject.put("current", currentExerciseMode);

            rtdbViewModel.saveSelectedExerciseMode(view.getContext(), exerciseTypeObject);

        });
        view.findViewById(R.id.plankCardView).setOnClickListener(view14 -> {
            Toast.makeText(view.getContext(),
                    "Plank exercises started.", Toast.LENGTH_SHORT).show();
            exerciseTypeObject = new HashMap<>();
            currentExerciseMode = new CurrentExerciseMode(
                    localDateTime.toLocalTime().toString(),
                    localDateTime.toLocalDate().toString(),
                    "plank");
            exerciseTypeObject.put("current", currentExerciseMode);

            rtdbViewModel.saveSelectedExerciseMode(view.getContext(), exerciseTypeObject);

        });

        view.findViewById(R.id.stopMaterialButton).setOnClickListener(view15 -> {
            Toast.makeText(view.getContext(),
                    "Exercise stopped!.", Toast.LENGTH_SHORT).show();
            exerciseTypeObject = new HashMap<>();
            currentExerciseMode = new CurrentExerciseMode(
                    localDateTime.toLocalTime().toString(),
                    localDateTime.toLocalDate().toString(),
                    "none");
            exerciseTypeObject.put("current", currentExerciseMode);

            rtdbViewModel.saveSelectedExerciseMode(view.getContext(), exerciseTypeObject);

        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseAuthenticationViewModel firebaseAuthenticationViewModel =
                new ViewModelProvider(this,
                        ViewModelProvider.Factory
                                .from(FirebaseAuthenticationViewModel.initializer))
                        .get(FirebaseAuthenticationViewModel.class);

        firebaseAuthenticationViewModel.signInUseWithEmailAndPassword();
    }
}