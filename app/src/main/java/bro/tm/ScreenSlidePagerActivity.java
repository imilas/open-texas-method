package bro.tm;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        @Override
        public int getItemPosition(Object obj){
            return POSITION_NONE;
        }

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                //setup page
                return new SetupFragment().newInstance();
            }else{
                Fragment fragment = new ScreenSlidePageFragment().newInstance(position);

                return fragment;
            }
        }
        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }

    public void updateMaxes(View v){
        //get the maxes from the view and put them in the ShareadPreferences


        ViewGroup setupLayout = (ViewGroup) this.findViewById(R.id.setup_layout);

        TextView tv = (TextView) setupLayout.findViewById(R.id.bench_max);
        String benchMax =  tv.getText().toString();
        tv = (TextView) setupLayout.findViewById(R.id.squat_max);
        String squatMax =  tv.getText().toString();
        tv = (TextView) setupLayout.findViewById(R.id.dead_lift_max);
        String deadLiftMax =  tv.getText().toString();

        SharedPreferences prefs = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("bench_max",benchMax);
        editor.putString("squat_max",squatMax);
        editor.putString("dead_lift_max",deadLiftMax);


        editor.commit();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            try {
                Bundle arg= fragment.getArguments();
                int pageNum=arg.getInt("pageNum");
                if (arg.getInt("pageNum")==1){
                    android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(fragment).attach(fragment).commit();

                }
            } catch (NullPointerException e) {

            }
        }


    }
}

