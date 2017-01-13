package models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import bro.tm.R;

/**
 * Created by amir on 06/05/16.
 */
public class plan {
    WorkoutWeek week1;
    WorkoutWeek week2;


    public plan(SharedPreferences prefs){

        String unit = prefs.getString("unit","lbs");
        String opt1 = "ohp";
        String opt2 = "row";
        String opt3 = "pullup";

        double benchMax = Double.parseDouble(prefs.getString("bench_max", "400"));
        double squatMax = Double.parseDouble(prefs.getString("squat_max", "300"));
        double liftMax = Double.parseDouble(prefs.getString("deadlift_max", "300"));
        double deadliftIncrement = Double.parseDouble(prefs.getString("deadlift_increment", "5"));
        double squatIncrement = Double.parseDouble(prefs.getString("squat_increment", "5"));
        double benchIncrement = Double.parseDouble(prefs.getString("bench_increment", "5"));
        double ohpMax = Double.parseDouble(prefs.getString("ohp_max", "300"));
        double ohpIncrement = Double.parseDouble(prefs.getString("ohp_increment", "5"));

        double frmFraction = 0.87; //five rep max fraction
        double fS=0.87 * frmFraction ;
        double fB=0.85 * frmFraction ;
        double fL=0.9 * frmFraction;

        //**********week1*******************

        //Volume
        ArrayList<Set> squatSetsMon= new ArrayList<>();
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        Exercise exercise1 = new Exercise("squat",squatSetsMon);

        ArrayList<Set> benchSetsMon= new ArrayList<>();
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        Exercise exercise3 = new Exercise("bench",benchSetsMon);

        ArrayList<Set> liftSetsMon= new ArrayList<>();
        liftSetsMon.add(new Set("5",calculateWeight(liftMax,fL,unit,deadliftIncrement)));
        Exercise exercise2 = new Exercise("deadlift",liftSetsMon);

        ArrayList<Exercise>  exercisesVol=new ArrayList<Exercise>();
        exercisesVol.add(exercise1);
        exercisesVol.add(exercise2);
        exercisesVol.add(exercise3);
        Workout volume=new Workout("Volume Day",exercisesVol);

        //***Wednesday********************

        ArrayList<Set> squatSetsWednesday= new ArrayList<>();
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        Exercise exercise7 = new Exercise("squat",squatSetsWednesday);

        ArrayList<Set> ohpSetsTue= new ArrayList<>();
        ohpSetsTue.add(new Set("8",calculateWeight(ohpMax,fB*0.7,unit,0)));
        ohpSetsTue.add(new Set("8",calculateWeight(ohpMax,fB*0.7,unit,0)));
        ohpSetsTue.add(new Set("8",calculateWeight(ohpMax,fB*0.7,unit,0)));
        Exercise exercise4 = new Exercise("ohp",ohpSetsTue);

        ArrayList<Set> opt2SetsTue= new ArrayList<>();
        opt2SetsTue.add(new Set("12"," "));
        opt2SetsTue.add(new Set("12"," "));
        opt2SetsTue.add(new Set("12"," "));
        Exercise exercise5 = new Exercise(opt2,opt2SetsTue);

        ArrayList<Set> opt3SetsTue= new ArrayList<>();
        opt3SetsTue.add(new Set("12"," "));
        opt3SetsTue.add(new Set("12"," "));
        opt3SetsTue.add(new Set("12"," "));
        Exercise exercise6 = new Exercise(opt3,opt3SetsTue);

        ArrayList<Exercise>  exercisesTue=new ArrayList<Exercise>();

        exercisesTue.add(exercise7);
        exercisesTue.add(exercise4);
        exercisesTue.add(exercise5);
        exercisesTue.add(exercise6);

        Workout reovery=new Workout("Recovery Day",exercisesTue);

        //Intensity day***************************************
        ArrayList<Set> squatSetsIntense= new ArrayList<>();
        squatSetsIntense.add(new Set("5",calculateWeight(squatMax,frmFraction,unit,squatIncrement)));
        exercise1 = new Exercise("squat",squatSetsIntense);


        ArrayList<Set> benchSetsIntense= new ArrayList<>();
        benchSetsIntense.add(new Set("5",calculateWeight(benchMax,frmFraction,unit,benchIncrement)));
        exercise3 = new Exercise("bench",benchSetsIntense);

        ArrayList<Set> liftSetsIntense= new ArrayList<>();
        liftSetsIntense.add(new Set("12",calculateWeight(liftMax,0.75,unit,0)));
        liftSetsIntense.add(new Set("12",calculateWeight(liftMax,0.75,unit,0)));
        liftSetsIntense.add(new Set("12",calculateWeight(liftMax,0.75,unit,0)));
        exercise2 = new Exercise("deadlift",liftSetsIntense);


        exercisesVol=new ArrayList<Exercise>();
        exercisesVol.add(exercise1);
        exercisesVol.add(exercise3);
        exercisesVol.add(exercise2);
        Workout intense=new Workout("Intensity Day",exercisesVol);

        ArrayList<Workout> workouts = new ArrayList<Workout>();
        workouts.add(volume);
        workouts.add(reovery);
        workouts.add(intense);

        this.week1 = new WorkoutWeek("Week1",workouts);



    }


    public WorkoutWeek getWeek1(){
        return this.week1;
    }
    public WorkoutWeek getWeek2(){
        return this.week2;
    }


    public String calculateWeight(double weight, double modifier, String weightUnit, double smallAdjustments) {
        switch (weightUnit) {
            case "kgs":
                return String.valueOf(roundToKgs(weight * modifier)+smallAdjustments);
            case "lbs":
                return String.valueOf(roundToLbs(weight * modifier)+smallAdjustments);
            default:
                return "";
        }
    }

    public int roundToLbs (double weight){
        return (int)((weight % 5 == 0) ? weight : weight + 5 - (weight % 5));
    }
    public int roundToKgs (Double weight) {
        return (int)((weight % 2.5 == 0) ? weight : weight + 2.5 - (weight % 2.5));
    }
}
