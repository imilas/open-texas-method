package bro.tm;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import models.Exercise;
import models.Set;
import models.Workout;
import models.plan;

import static android.support.design.R.id.center;
import static android.widget.LinearLayout.LayoutParams.*;

public class ScreenSlidePageFragment extends Fragment {

    public static ScreenSlidePageFragment newInstance(int position){
        ScreenSlidePageFragment thisFragment= new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("pageNum", position);
        thisFragment.setArguments(args);
        return thisFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup planView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page , container, false);
        LinearLayout rootView= (LinearLayout) planView.findViewById(R.id.content);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        plan plan = new plan(prefs);

        for(Workout workout : plan.getWeek1().workouts){

            ViewGroup row = (ViewGroup) inflater.inflate(R.layout.exercise_layout , container, false);
            TextView workoutDay = (TextView)row.findViewById(R.id.workout_day);
            workoutDay.setText(workout.day);
            rootView.addView(row);

            //get the plan and transform create the layout based on it for it and add it to the view
            for(Exercise exercise : workout.exercises){

                LinearLayout setView = new LinearLayout(this.getContext());
                setView.setOrientation((LinearLayout.HORIZONTAL));
                setView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));

                SingleLineTextView setName = new SingleLineTextView(this.getContext());

                setName.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1f));
                setName.setText(exercise.exerciseName);
                setName.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                setView.setGravity(center);
                setView.addView(setName);
                setView.setBackgroundResource(R.drawable.customborder);

                for (Set set : exercise.sets){
                    //make a layout with 2 text views on top of each other for sets and reps

                    ViewGroup setXRepsLayout = (ViewGroup) inflater.inflate(R.layout.set_x_rep_layout, container, false);
                    setXRepsLayout.setLayoutParams(new LayoutParams(WRAP_CONTENT,WRAP_CONTENT,1f));

                    SingleLineTextView setWeight = (SingleLineTextView) setXRepsLayout.findViewById(R.id.set_weight);
                    SingleLineTextView setReps = (SingleLineTextView) setXRepsLayout.findViewById(R.id.set_rep);

                    setWeight.setText(set.Weight);
                    setReps.setText("x "+set.Reps);

                    setReps.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                    setWeight.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

                    setView.addView(setXRepsLayout);
                }

                rootView.addView(setView);
            }

        }
        return planView;
    }

}
