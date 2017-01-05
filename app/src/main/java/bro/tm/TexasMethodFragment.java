package bro.tm;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import me.grantland.widget.AutofitTextView;
import models.Exercise;
import models.Set;
import models.Workout;
import models.plan;

import static android.widget.LinearLayout.LayoutParams.*;

public class TexasMethodFragment extends Fragment {

    public static TexasMethodFragment newInstance(){
        TexasMethodFragment thisFragment= new TexasMethodFragment();
        return thisFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup planView = (ViewGroup) inflater.inflate(R.layout.plan_layout, container, false);
        LinearLayout rootView= (LinearLayout) planView.findViewById(R.id.content);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        plan plan = new plan(prefs);
        for(Workout workout : plan.getWeek1().workouts){

            ViewGroup row = (ViewGroup) inflater.inflate(R.layout.exercise_layout , container, false);
            row.setBackgroundResource(R.drawable.customborder);
            TextView workoutDay = (TextView)row.findViewById(R.id.workout_day);
            workoutDay.setText(workout.day);
            workoutDay.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
            workoutDay.setTextColor(Color.BLACK);
            rootView.addView(row);

            for(Exercise exercise : workout.exercises){

                LinearLayout setView = new LinearLayout(this.getContext());
                setView.setOrientation((LinearLayout.HORIZONTAL));
                setView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                setView.setBackgroundResource(R.drawable.set_border);
                AutofitTextView setName = new AutofitTextView(this.getContext());

                setName.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT,0.2f));


                setName.setGravity(Gravity.CENTER);
                setName.setText(exercise.exerciseName);
                setName.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
                setName.setBackgroundColor(Color.GREEN);
                setName.setBackgroundColor(Color.parseColor("#adccff"));
                setView.addView(setName);

                LinearLayout numberContainer = new LinearLayout(this.getContext());
                numberContainer.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT,0.8f));
                for (Set set : exercise.sets){
                    //make a layout with 2 text views on top of each other for sets and reps

                    ViewGroup setXRepsLayout = (ViewGroup) inflater.inflate(R.layout.set_x_rep_layout, container, false);
                    setXRepsLayout.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT,1f));

                    AutofitTextView setWeight = (AutofitTextView) setXRepsLayout.findViewById(R.id.set_weight);
                    AutofitTextView setReps = (AutofitTextView) setXRepsLayout.findViewById(R.id.set_rep);
                    setWeight.setText(set.Weight);
                    setReps.setText("x"+set.Reps);

                    setReps.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

                    setWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    numberContainer.addView(setXRepsLayout);
                }
                setView.addView(numberContainer);
                rootView.addView(setView);
            }

        }
        return planView;
    }

}
