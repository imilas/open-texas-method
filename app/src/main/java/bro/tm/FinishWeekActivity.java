package bro.tm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Switch;

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

        Switch rb = (Switch) findViewById(R.id.deadlift_switch);
        Switch rd = (Switch) findViewById(R.id.bench_switch);
        Switch rs = (Switch) findViewById(R.id.squat_switch);

        SharedPreferences prefs = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        double benchMax = Double.parseDouble(prefs.getString("bench_max", "400"));
        double squatMax = Double.parseDouble(prefs.getString("squat_max", "300"));
        double deadLiftMax = Double.parseDouble(prefs.getString("deadlift_max", "300"));

        if(rb.isChecked()){
            editor.putString("bench_max",String.valueOf(benchMax+5));
        }
        if(rs.isChecked()){
            editor.putString("squat_max", String.valueOf(squatMax+5));
        }
        if(rd.isChecked()){
            editor.putString("dead_lift_max",String.valueOf(deadLiftMax+5));
        }
        editor.commit();
        this.finish();

    }
}
