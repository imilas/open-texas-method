package models;

import java.util.ArrayList;

/**
* Created by amir on 06/05/16.
*/ // Exercises contain exerise name/description and set information
// Ex.["Squat", [Set[100,6], Set[100,6]]]
public class Exercise{
    public String exerciseName;
    public ArrayList<Set> sets = new ArrayList<Set>();

    Exercise(String name, ArrayList<Set> sets){
        this.exerciseName=name;
        this.sets=sets;
    }

    //TODO Should these utility funcs be in the struct?
    public double calculateWeight(double weight, double modifier, String weightUnit) {
        switch (weightUnit) {
            case "kg":
                return roundToKgs(weight * modifier);
            case "lb":
                return roundToLbs(weight * modifier);
            default:
                return 0;
        }
    }

    public int roundToLbs (double weight){
        return (int)((weight % 5 == 0) ? weight : weight + 5 - (weight % 5));
    }
    public int roundToKgs (Double weight) {
        return (int)((weight % 2.5 == 0) ? weight : weight + 2.5 - (weight % 2.5));
    }
}
