package bro.tm;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by root on 04/12/16.
 */
import models.plan;
public class ScreenSlidePageFragment extends Fragment {

    public static ScreenSlidePageFragment newInstance(int position){
        ScreenSlidePageFragment thisFragment= new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("pageNum", position);
        thisFragment.setArguments(args);
        return thisFragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page , container, false);
        TextView v= (TextView) rootView.findViewById(R.id.page_number);
        int pageNum=getArguments().getInt("pageNum",0);
        v.setText("Day" + Integer.toString(pageNum));

        //add exercises
        SharedPreferences prefs = this.getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        plan plan = new plan(prefs);

        ViewGroup exercise1 = (ViewGroup) inflater.inflate(R.layout.exercise_layout , container, false);


        rootView.addView(exercise1);

        return rootView;
    }

}
