package bro.tm;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by root on 04/12/16.
 */
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

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page , container, false);
        TextView v= (TextView) rootView.findViewById(R.id.page_number);
        int pageNum=getArguments().getInt("pageNum",0);
        v.setText("Day" + Integer.toString(pageNum));

        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        plan plan = new plan(prefs);

        for(Workout workout : plan.getWeek1().workouts){

            ViewGroup row = (ViewGroup) inflater.inflate(R.layout.exercise_layout , container, false);

            TextView workoutDay = (TextView)row.findViewById(R.id.workout_day);
            workoutDay.setText(workout.day);

            for(Exercise exercise : workout.exercises){
                LinearLayout setView = new LinearLayout(this.getContext());
                setView.setOrientation((LinearLayout.HORIZONTAL));
                setView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));

                TextView setName = new TextView(this.getContext());
                setName.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f));
                setName.setText(exercise.exerciseName);

                setView.addView(setName);
                for (Set set : exercise.sets){
                    TextView setWeightAndReps = new TextView(this.getContext());
                    TextView tv = new TextView(v.getContext());

                    setWeightAndReps.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));

                    setWeightAndReps.setText(set.Reps + "x"+set.Weight);
                    setView.addView(setWeightAndReps);
                }
                rootView.addView(setView);
            }
              rootView.addView(row);
        }
        return rootView;
    }

}
