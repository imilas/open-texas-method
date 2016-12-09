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

        //**********week1*******************

        //monday
        ArrayList<Set> squatSetsMon= new ArrayList<>();
        squatSetsMon.add(new Set("6",calculateWeight(squatMax,0.8,unit,0)));
        squatSetsMon.add(new Set("6",calculateWeight(squatMax,0.8,unit,0)));
        squatSetsMon.add(new Set("6",calculateWeight(squatMax,0.8,unit,0)));
        squatSetsMon.add(new Set("6",calculateWeight(squatMax,0.8,unit,0)));
        Exercise exercise1 = new Exercise("squat",squatSetsMon);

        ArrayList<Set> liftSetsMon= new ArrayList<>();
        liftSetsMon.add(new Set("6",calculateWeight(liftMax,0.8,unit,0)));
        liftSetsMon.add(new Set("6",calculateWeight(liftMax,0.8,unit,0)));
        Exercise exercise2 = new Exercise("deadlift",liftSetsMon);

        ArrayList<Exercise>  exercisesMon=new ArrayList<Exercise>();
        exercisesMon.add(exercise1);
        exercisesMon.add(exercise2);
        Workout monday=new Workout("monday",exercisesMon);

        //***Tuesday***
        ArrayList<Set> benchSetsTue= new ArrayList<>();
        benchSetsTue.add(new Set("10",calculateWeight(benchMax,0.5,unit,0)));
        benchSetsTue.add(new Set("10",calculateWeight(benchMax,0.675,unit,0)));
        benchSetsTue.add(new Set("8",calculateWeight(benchMax,0.75,unit,0)));
        benchSetsTue.add(new Set("6",calculateWeight(benchMax,0.775,unit,0)));
        Exercise exercise3 = new Exercise("bench",benchSetsTue);

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
        exercisesTue.add(exercise3);
        exercisesTue.add(exercise4);
        exercisesTue.add(exercise5);
        exercisesTue.add(exercise6);

        Workout tuesday=new Workout("Tuesday",exercisesTue);

        //****thursday****
        ArrayList<Set> benchSetsThu= new ArrayList<>();
        benchSetsThu.add(new Set("10",calculateWeight(benchMax,0.5,unit,0)));
        benchSetsThu.add(new Set("10",calculateWeight(benchMax,0.675,unit,0)));
        benchSetsThu.add(new Set("8",calculateWeight(benchMax,0.75,unit,0)));
        benchSetsThu.add(new Set("6",calculateWeight(benchMax,0.775,unit,0)));
        Exercise exercise7 = new Exercise("bench",benchSetsThu);

        ArrayList<Set> opt1SetsThu= new ArrayList<>();
        opt1SetsThu.add(new Set("10"," "));
        opt1SetsThu.add(new Set("10"," "));
        opt1SetsThu.add(new Set("8"," "));
        opt1SetsThu.add(new Set("6"," "));
        Exercise exercise8 = new Exercise(opt1,opt1SetsThu);

        ArrayList<Set> opt2SetsThu= new ArrayList<>();
        opt2SetsThu.add(new Set("12"," "));
        opt2SetsThu.add(new Set("12"," "));
        opt2SetsThu.add(new Set("10"," "));
        opt2SetsThu.add(new Set("8"," "));
        Exercise exercise9 = new Exercise(opt2,opt2SetsThu);

        ArrayList<Set> opt3SetsThu= new ArrayList<>();
        opt3SetsThu.add(new Set("12"," "));
        opt3SetsThu.add(new Set("12"," "));
        opt3SetsThu.add(new Set("10"," "));
        opt3SetsThu.add(new Set("8"," "));
        Exercise exercise10 = new Exercise(opt3,opt3SetsThu);

        ArrayList<Exercise>  exercisesThu=new ArrayList<Exercise>();
        exercisesThu.add(exercise3);
        exercisesThu.add(exercise4);
        exercisesThu.add(exercise5);
        exercisesThu.add(exercise6);

        Workout thursday=new Workout("Thursday",exercisesThu);

        //****Friday****
        ArrayList<Set> squatSetsFri= new ArrayList<>();

        squatSetsFri.add(new Set("8",calculateWeight(squatMax,0.7,unit,0)));
        squatSetsFri.add(new Set("8",calculateWeight(squatMax,0.7,unit,0)));
        squatSetsFri.add(new Set("8",calculateWeight(squatMax,0.7,unit,0)));
        squatSetsFri.add(new Set("8",calculateWeight(squatMax,0.7,unit,0)));
        Exercise exercise11 = new Exercise("squat",squatSetsFri);

        ArrayList<Set> liftSetsFri= new ArrayList<>();
        liftSetsFri.add(new Set("8",calculateWeight(liftMax,0.7,unit,0)));
        Exercise exercise12 = new Exercise("deadlift",liftSetsFri);

        ArrayList<Exercise>  exercisesFri=new ArrayList<Exercise>();
        exercisesFri.add(exercise11);
        exercisesFri.add(exercise12);
        Workout friday=new Workout("Friday",exercisesFri);

        //****Sunday********
        ArrayList<Set> benchSetsSun= new ArrayList<>();
        benchSetsSun.add(new Set("MR",calculateWeight(benchMax,0.8,unit,0)));
        Exercise exercise13 = new Exercise("bench",benchSetsSun);

        ArrayList<Set> opt1SetsSun= new ArrayList<>();
        opt1SetsSun.add(new Set("10"," "));
        opt1SetsSun.add(new Set("10"," "));
        opt1SetsSun.add(new Set("8"," "));
        opt1SetsSun.add(new Set("6"," "));
        Exercise exercise14 = new Exercise(opt1,opt1SetsSun);

        ArrayList<Set> opt2SetsSun= new ArrayList<>();
        opt2SetsSun.add(new Set("12"," "));
        opt2SetsSun.add(new Set("12"," "));
        opt2SetsSun.add(new Set("10"," "));
        opt2SetsSun.add(new Set("8"," "));
        Exercise exercise15 = new Exercise(opt2,opt2SetsSun);

        ArrayList<Set> opt3SetsSun= new ArrayList<>();
        opt3SetsSun.add(new Set("12"," "));
        opt3SetsSun.add(new Set("12"," "));
        opt3SetsSun.add(new Set("10"," "));
        opt3SetsSun.add(new Set("8"," "));
        Exercise exercise16 = new Exercise(opt3,opt3SetsSun);

        ArrayList<Exercise>  exercisesSun=new ArrayList<Exercise>();
        exercisesSun.add(exercise3);
        exercisesSun.add(exercise4);
        exercisesSun.add(exercise5);
        exercisesSun.add(exercise6);

        Workout sunday=new Workout("Sunday",exercisesSun);

        //done!
        ArrayList<Workout> workouts=new ArrayList<Workout>();
        workouts.add(monday);
        workouts.add(tuesday);
        workouts.add(thursday);
        workouts.add(friday);
        workouts.add(sunday);

        this.week1 = new WorkoutWeek("linearmax",workouts);

       // ********************week2*******************************



    }


    public WorkoutWeek getWeek1(){
        return this.week1;
    }
    public WorkoutWeek getWeek2(){
        return this.week2;
    }

    //TODO Should these utility funcs be in the struct?
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
