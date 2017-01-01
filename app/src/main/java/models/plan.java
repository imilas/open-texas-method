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



        String unit = "lb";
        String opt1 = "ohp";
        String opt2 = "row";
        String opt3 = "pullup";
//        SharedPreferences.Editor edit = prefs.edit();
//        edit.putString("squatMax", "300");
//        edit.putString("benchMax", "225");
//        edit.putString("liftMax", "300");
//        edit.commit();
        double benchMax = Double.parseDouble(prefs.getString("bench_max", "400"));
        double squatMax = Double.parseDouble(prefs.getString("squat_max", "300"));
        double liftMax = Double.parseDouble(prefs.getString("deadlift_max", "300"));
        double deadliftIncrement = Double.parseDouble(prefs.getString("deadlift_increment", "5"));
        double squatIncrement = Double.parseDouble(prefs.getString("squat_increment", "5"));
        double benchIncrement = Double.parseDouble(prefs.getString("bench_increment", "5"));

        double fS=0.87;
        double fB=0.8;
        double fL=0.9;

        //**********week1*******************

        //monday
        ArrayList<Set> squatSetsMon= new ArrayList<>();
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        squatSetsMon.add(new Set("5",calculateWeight(squatMax,fS,unit,0)));
        Exercise exercise1 = new Exercise("squat",squatSetsMon);

        ArrayList<Set> liftSetsMon= new ArrayList<>();
        liftSetsMon.add(new Set("5",calculateWeight(liftMax,fL,unit,deadliftIncrement)));
        Exercise exercise2 = new Exercise("deadlift",liftSetsMon);

        ArrayList<Set> benchSetsMon= new ArrayList<>();
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        benchSetsMon.add(new Set("5",calculateWeight(benchMax,fB,unit,0)));
        Exercise exercise3 = new Exercise("bench",benchSetsMon);

        ArrayList<Exercise>  exercisesVol=new ArrayList<Exercise>();
        exercisesVol.add(exercise1);
        exercisesVol.add(exercise2);
        exercisesVol.add(exercise3);
        Workout volume=new Workout("Volume Day",exercisesVol);

        //***Wednesday***

        //monday
        ArrayList<Set> squatSetsWednesday= new ArrayList<>();
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        squatSetsWednesday.add(new Set("5",calculateWeight(squatMax,fS*0.9,unit,0)));
        Exercise exercise7 = new Exercise("squat",squatSetsWednesday);

        ArrayList<Set> opt1SetsTue= new ArrayList<>();
        opt1SetsTue.add(new Set("10"," "));
        opt1SetsTue.add(new Set("10"," "));
        opt1SetsTue.add(new Set("8"," "));
        opt1SetsTue.add(new Set("6"," "));
        Exercise exercise4 = new Exercise(opt1,opt1SetsTue);

        ArrayList<Set> opt2SetsTue= new ArrayList<>();
        opt2SetsTue.add(new Set("12"," "));
        opt2SetsTue.add(new Set("12"," "));
        opt2SetsTue.add(new Set("10"," "));
        opt2SetsTue.add(new Set("8"," "));
        Exercise exercise5 = new Exercise(opt2,opt2SetsTue);

        ArrayList<Set> opt3SetsTue= new ArrayList<>();
        opt3SetsTue.add(new Set("12"," "));
        opt3SetsTue.add(new Set("12"," "));
        opt3SetsTue.add(new Set("10"," "));
        opt3SetsTue.add(new Set("8"," "));
        Exercise exercise6 = new Exercise(opt3,opt3SetsTue);

        ArrayList<Exercise>  exercisesTue=new ArrayList<Exercise>();

        exercisesTue.add(exercise7);
        exercisesTue.add(exercise4);
        exercisesTue.add(exercise5);
        exercisesTue.add(exercise6);

        Workout reovery=new Workout("Recovery Day",exercisesTue);

        //Intensity day
        //**********week1*******************

        //friday usually
        ArrayList<Set> squatSetsIntense= new ArrayList<>();
        squatSetsIntense.add(new Set("5",calculateWeight(squatMax,1,unit,squatIncrement)));
        exercise1 = new Exercise("squat",squatSetsIntense);

        ArrayList<Set> liftSetsIntense= new ArrayList<>();
        liftSetsIntense.add(new Set("12",calculateWeight(liftMax,0.75,unit,0)));
        liftSetsIntense.add(new Set("12",calculateWeight(liftMax,0.75,unit,0)));
        liftSetsIntense.add(new Set("12",calculateWeight(liftMax,0.75,unit,0)));
        exercise2 = new Exercise("deadlift",liftSetsIntense);

        ArrayList<Set> benchSetsIntense= new ArrayList<>();
        benchSetsIntense.add(new Set("5",calculateWeight(benchMax,1,unit,benchIncrement)));
        benchSetsIntense.add(new Set("5",calculateWeight(benchMax,1,unit,benchIncrement)));
        benchSetsIntense.add(new Set("5",calculateWeight(benchMax,1,unit,benchIncrement)));
        exercise3 = new Exercise("bench",benchSetsIntense);

        exercisesVol=new ArrayList<Exercise>();
        exercisesVol.add(exercise1);
        exercisesVol.add(exercise2);
        exercisesVol.add(exercise3);
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
            case "kg":
                return String.valueOf(roundToKgs(weight * modifier)+smallAdjustments);
            case "lb":
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
