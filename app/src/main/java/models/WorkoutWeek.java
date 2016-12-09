package models;

import java.util.ArrayList;

    public class WorkoutWeek{
        public String name="monday";
        public ArrayList<Workout> workouts;

        public WorkoutWeek(String name,ArrayList<Workout> workouts) {
            this.workouts = workouts;
            this.name = name;
        }
//
//        public WorkoutWeek getWeek1(){
//
//            SetData set1= new SetData(50,10);
//            ArrayList<SetData>  setDatas = new ArrayList<SetData>();
//            setDatas.add(set1);
//
//            Exercise exercise1 = new Exercise("squat",setDatas);
//            ArrayList<Exercise>  exercises=new ArrayList<Exercise>();
//            exercises.add(exercise1);
//
//            Workout monday=new Workout("monday",exercises);
//            ArrayList<Workout> workouts=new ArrayList<Workout>();
//            workouts.add(monday);
//
//            WorkoutWeek week1 = new WorkoutWeek("linearmax",workouts);
//            return week1;
//        }

    }






