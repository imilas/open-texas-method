package models;

import java.util.ArrayList;

/**
 * Created by amir on 06/05/16.
 */ // Workouts contain date to be done, and exercises
    // Ex ["Sun",[Exercise[["Squat", [Set[100,6], Set[100,6]]]], Exercise[["Deadlift", [Set[100,6], Set[100,6]]]]]]]
    public class Workout{
        public String day;
        public ArrayList<Exercise> exercises;

        Workout(String name, ArrayList<Exercise> exercisesPassed){
            day=name;
            exercises=exercisesPassed;

        }

    }
