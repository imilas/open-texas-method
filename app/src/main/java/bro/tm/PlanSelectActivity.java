package bro.tm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Amir on 26/06/2017.
 */

public class PlanSelectActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_select);
        final SharedPreferences prefs = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        Spinner planSpinner = (Spinner) findViewById(R.id.plans_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plans_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planSpinner.setAdapter(adapter);

        String planFlavor = prefs.getString("plan_type","Powerlifting TM");
        planSpinner.setSelection(((ArrayAdapter)planSpinner.getAdapter()).getPosition(planFlavor));

        planSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("plan_type",parent.getItemAtPosition(position).toString());
                editor.commit();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


}
