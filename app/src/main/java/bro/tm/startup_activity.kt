package bro.tm

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Amir on 3/07/2017.
 */

class startup_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = this.getSharedPreferences("maxes", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        try {
            java.lang.Double.parseDouble(prefs.getString("bench_max", ""))
        } catch(e: Exception) {
            editor.putString("bench_max","225")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("squat_max", ""))
        } catch(e: Exception) {
            editor.putString("squat_max","315")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("deadlift_max", ""))
        } catch(e: Exception) {
            editor.putString("deadlift_max","375")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("ohp_max", ""))
        } catch(e: Exception) {
            editor.putString("ohp_max","185")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("clean_max", ""))
        } catch(e: Exception) {
            editor.putString("clean_max","135")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("bench_increment", ""))
        } catch(e: Exception) {
            editor.putString("bench_increment","5")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("deadlift_increment", ""))
        } catch(e: Exception) {
            editor.putString("deadlift_increment","5")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("squat_increment", ""))
        } catch(e: Exception) {
            editor.putString("squat_increment","5")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("ohp_increment", ""))
        } catch(e: Exception) {
            editor.putString("ohp_increment","5")
        }
        try {
            java.lang.Double.parseDouble(prefs.getString("clean_increment", ""))
        } catch(e: Exception) {
            editor.putString("clean_increment","5")
        }
        if(prefs.getString("plan_type", "")==""){
            editor.putString("plan_type","Powerlifting TM")
        }
        editor.commit();

        this.finish();
    }

}


