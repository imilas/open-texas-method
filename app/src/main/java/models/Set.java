package models;

/**
 * Created by amir on 25/04/16.
 */


    // Set contains weight for an exercise and the number of repetitions
    // Would be presented as 100 x6
    public class Set {
        public String Weight ="";
        //public double modifier =0;
        public String Reps = "";

    public Set(String reps, String weight) {
        //this.modifier = modifier;
        Reps = reps;
        Weight = weight;
    }
}
