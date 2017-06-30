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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import events.DatabaseUpdate;
import me.grantland.widget.AutofitTextView;
import models.Exercise;
import models.PlanPowerlifting;
import models.Set;
import models.ThePlan;
import models.Workout;
import models.PlanVanilla;

import static android.widget.LinearLayout.LayoutParams.*;

public class PlanFragment extends Fragment {
    public int WeekNumber;
    public ViewGroup planView = null;
    public ViewGroup container = null;
    public LayoutInflater inflater = null;

    public PlanFragment newInstance(){
        PlanFragment thisFragment= new PlanFragment();
        return thisFragment;
    }

    public void setWeekNumber(int index){
        WeekNumber=index;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        this.inflater=inflater;
        this.container = container;

        planView = (ViewGroup) inflater.inflate(R.layout.fragment_plan_layout, container, false);
        SharedPreferences prefs = this.getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String planFlavor = prefs.getString("plan_type","Powerlifting TM");
        //planFlavor = "General Strength TM";
        ThePlan plan = null;

        if (planFlavor.equals("General Strength TM")){
            plan= new PlanVanilla(prefs);
        }else if(planFlavor.equals("Powerlifting TM")){
            plan = new PlanPowerlifting(prefs);
        }
        LinearLayout rootView= (LinearLayout) planView.findViewById(R.id.content);

        for(Workout workout : plan.getWeek(WeekNumber).workouts){
            // Workouts contain date to be done, and exercises
            // Ex ["VolumeDay",[Exercise[["Squat", [Set[100,6], Set[100,6]]]], Exercise[["Deadlift", [Set[100,6], Set[100,6]]]]]]]

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

                //the warmup row is created here
                try{
                    ViewGroup warmupLayout = (ViewGroup) inflater.inflate(R.layout.warmup_row, container, false);
                    setupWarmUps(Float.parseFloat(exercise.sets.get(0).Weight),warmupLayout);
                    rootView.addView(warmupLayout);
                }catch (NumberFormatException e){
                    //sometimes no weight is specified so no need for warmups i guess
                }

            }

        }

        return planView;

    }
    public void updateView(){


    }

    private  void setupWarmUps(double firstSetWeight, ViewGroup warmUpRow){

        ((AutofitTextView) warmUpRow.findViewById(R.id.set_weight1)).setText(String.valueOf(roundNum(firstSetWeight*0.3)));
        ((AutofitTextView) warmUpRow.findViewById(R.id.set_rep1)).setText("8");

        ((AutofitTextView) warmUpRow.findViewById(R.id.set_weight2)).setText(String.valueOf(roundNum(firstSetWeight*0.5)));
        ((AutofitTextView) warmUpRow.findViewById(R.id.set_rep2)).setText("5");

        ((AutofitTextView) warmUpRow.findViewById(R.id.set_weight3)).setText(String.valueOf(roundNum(firstSetWeight*0.65)));
        ((AutofitTextView) warmUpRow.findViewById(R.id.set_rep3)).setText("3");

        ((AutofitTextView) warmUpRow.findViewById(R.id.set_weight4)).setText(String.valueOf(roundNum(firstSetWeight*0.85)));
        ((AutofitTextView) warmUpRow.findViewById(R.id.set_rep4)).setText("2");

    }

    public int roundNum (double weight){
        return (int)((weight % 5 == 0) ? weight : weight + 5 - (weight % 5));
    }

    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void DatabaseUpdate(DatabaseUpdate event) {
        planView.invalidate();
        updateView();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
