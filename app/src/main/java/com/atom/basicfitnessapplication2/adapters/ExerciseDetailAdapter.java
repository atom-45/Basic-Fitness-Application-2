package com.atom.basicfitnessapplication2.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.atom.basicfitnessapplication.R;
import com.atom.basicfitnessapplication2.activities.ExerciseDataActivity;
import com.atom.basicfitnessapplication2.models.Constants;
import com.atom.basicfitnessapplication2.models.ExerciseDetail;

import java.util.List;

public class ExerciseDetailAdapter extends RecyclerView.Adapter<ExerciseDetailAdapter.ViewHolder> {

    private final List<ExerciseDetail> exerciseDetails;
    public ExerciseDetailAdapter(List<ExerciseDetail> exerciseDetails){
        this.exerciseDetails = exerciseDetails;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_detail_cardview,parent,false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView exerciseDetailCardView = holder.cardView;

        ImageView exerciseImageView = exerciseDetailCardView.findViewById(R.id.exerciseIconImageView2);
        TextView exerciseNameTextView = exerciseDetailCardView.findViewById(R.id.exerciseNameTextView1);
        TextView exerciseFocusTextView = exerciseDetailCardView.findViewById(R.id.mainBodyPartTextView1);

        exerciseImageView.setImageResource(exerciseDetails.get(position).getExerciseImageID());
        exerciseNameTextView.setText(exerciseDetails.get(position).getExerciseName());
        exerciseFocusTextView.setText(exerciseDetails.get(position).getExerciseFocus());

        exerciseDetailCardView.setOnClickListener(view ->
                view.getContext().startActivity(new Intent(view.getContext(),
                        ExerciseDataActivity.class).putExtra(Constants.EXERCISE_TYPE,
                        exerciseDetails.get(position).getExerciseName())));

    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        public ViewHolder(@NonNull CardView cv) {
            super(cv);
            this.cardView = cv;
        }
    }
}
