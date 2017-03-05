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
public abstract  class ThePlan {
    ArrayList<WorkoutWeek> planAlternatives = new ArrayList<>();
    SharedPreferences prefs;
    String unit;
    String opt1 = "ohp";
    String opt2 = "row";
    String opt3 = "pullup";

    double benchMax;
    double squatMax;
    double liftMax;
    double deadliftIncrement;
    double squatIncrement;
    double benchIncrement;
    double ohpMax;
    double ohpIncrement;

    double frmFraction = 0.87; //five rep max fraction
    double fS=0.87 * frmFraction ;
    double fB=0.85 * frmFraction ;
    double fL=0.9 * frmFraction;

    public ThePlan(SharedPreferences activityPrefs){
        prefs=activityPrefs;

        unit = prefs.getString("unit","lbs");

        benchMax = Double.parseDouble(prefs.getString("bench_max", "400"));
        squatMax = Double.parseDouble(prefs.getString("squat_max", "300"));
        liftMax = Double.parseDouble(prefs.getString("deadlift_max", "300"));
        deadliftIncrement = Double.parseDouble(prefs.getString("deadlift_increment", "5"));
        squatIncrement = Double.parseDouble(prefs.getString("squat_increment", "5"));
        benchIncrement = Double.parseDouble(prefs.getString("bench_increment", "5"));
        ohpMax = Double.parseDouble(prefs.getString("ohp_max", "300"));
        ohpIncrement = Double.parseDouble(prefs.getString("ohp_increment", "5"));

        planAlternatives.add(week1());
        planAlternatives.add(week2());

    }


    public WorkoutWeek getWeek(int index){
        return planAlternatives.get(index);
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

    public double roundToLbs (double weight){
        return (double)((weight % 5 == 0) ? weight : weight + 5 - (weight % 5));
    }
    public double roundToKgs (Double weight) {
        return (double)((weight % 2.5 == 0) ? weight : weight + 2.5 - (weight % 2.5));
    }

    public abstract WorkoutWeek week1();

    public abstract WorkoutWeek week2();


}
