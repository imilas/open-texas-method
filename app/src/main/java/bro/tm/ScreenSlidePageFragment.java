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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f);
                LinearLayout setView = new LinearLayout(this.getContext());
                setView.setOrientation((LinearLayout.HORIZONTAL));
                setView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));

                TextView setName = new TextView(this.getContext());

                setName.setLayoutParams(params);
                setName.setText(exercise.exerciseName);
                setName.setGravity(Gravity.CENTER);
                setName.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                setView.addView(setName);
                setView.setBackgroundResource(R.drawable.customborder);
                for (Set set : exercise.sets){
                    TextView setWeightAndReps = new TextView(this.getContext());
                    setWeightAndReps.setLayoutParams(params);
                    if(set.Weight.length()>4){
                        setWeightAndReps.setText(set.Weight + "\n x"+set.Reps);
                    }else{
                        setWeightAndReps.setText(set.Weight + "x"+set.Reps);
                    }
                    setWeightAndReps.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                    setView.addView(setWeightAndReps);

                }

                rootView.addView(setView);
            }

        }
        return planView;
    }

}
