package bro.tm;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 06/12/16.
 */

public class SetupFragment extends Fragment {

    public static SetupFragment newInstance(){
        SetupFragment thisFragment = new SetupFragment();
        return thisFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setup_layout, container, false);
        TextView v = (TextView) rootView.findViewById(R.id.squat_max);
        v.setText(prefs.getString("squat_max","0"));
        v = (TextView) rootView.findViewById(R.id.deadlift_max);
        v.setText(prefs.getString("deadlift_max","0"));
        v = (TextView) rootView.findViewById(R.id.bench_max);
        v.setText(prefs.getString("bench_max","0"));
        v = (TextView) rootView.findViewById(R.id.bench_increment);
        v.setText(prefs.getString("bench_increment","0"));
        v = (TextView) rootView.findViewById(R.id.squat_increment);
        v.setText(prefs.getString("squat_increment","0"));
        v = (TextView) rootView.findViewById(R.id.deadlift_increment);
        v.setText(prefs.getString("deadlift_increment","0"));


        return rootView;
    }

}
