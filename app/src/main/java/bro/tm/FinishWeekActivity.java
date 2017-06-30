package bro.tm;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Switch;

import org.greenrobot.eventbus.EventBus;

import events.DatabaseUpdate;
import models.WeeklyMaxLogsHelper;

/**
 * Created by root on 28/12/16.
 */

public class FinishWeekActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_week);
    }

    public void weekFinishedConfirmed(View v){

        Switch rd = (Switch) findViewById(R.id.deadlift_switch);
        Switch rb = (Switch) findViewById(R.id.bench_switch);
        Switch rs = (Switch) findViewById(R.id.squat_switch);
        Switch ro = (Switch) findViewById(R.id.ohp_switch);
        Switch rc = (Switch) findViewById(R.id.clean_switch);

        SharedPreferences prefs = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        double benchMax = Double.parseDouble(prefs.getString("bench_max", "225"));
        double squatMax = Double.parseDouble(prefs.getString("squat_max", "303"));
        double deadLiftMax = Double.parseDouble(prefs.getString("deadlift_max", "420"));
        double ohpMax = Double.parseDouble(prefs.getString("ohp_max", "225"));
        double cleanMax = Double.parseDouble(prefs.getString("clean_max", "185"));
        double benchIncrement = Double.parseDouble(prefs.getString("bench_increment", "5"));
        double  deadliftIncrement= Double.parseDouble(prefs.getString("deadlift_increment", "5"));
        double  squatIncrement= Double.parseDouble(prefs.getString("squat_increment", "5"));
        double ohpIncrement = Double.parseDouble(prefs.getString("ohp_increment", "5"));
        double cleanIncrement = Double.parseDouble(prefs.getString("clean_increment", "5"));


        WeeklyMaxLogsHelper logs = new WeeklyMaxLogsHelper(this);
        SQLiteDatabase db = logs.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("squat", squatMax);
        values.put("bench", benchMax);
        values.put("deadlift", deadLiftMax);
        values.put("ohp", ohpMax);
        values.put("clean", cleanMax);
        long newRowId = db.insert("logs", null, values);

        if(rb.isChecked()){
            editor.putString("bench_max",String.valueOf(benchMax+benchIncrement));
        }
        if(rs.isChecked()){
            editor.putString("squat_max", String.valueOf(squatMax+squatIncrement));
        }
        if(rd.isChecked()){
            editor.putString("deadlift_max",String.valueOf(deadLiftMax+deadliftIncrement));
        }
        if(ro.isChecked()){
            editor.putString("ohp_max",String.valueOf(ohpIncrement+ohpMax));
        }
        if(rc.isChecked()){
            editor.putString("clean_max",String.valueOf(cleanIncrement+cleanMax));
        }
        editor.commit();
        EventBus.getDefault().postSticky(new DatabaseUpdate("Plan refreshed!"));


        this.finish();

    }
}
