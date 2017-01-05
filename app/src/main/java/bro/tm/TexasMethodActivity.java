package bro.tm;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TexasMethodActivity extends AppCompatActivity{

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ActionBar actionBar = getActionBar();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsies);
        tabLayout.setupWithViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                Fragment fragment = new TexasMethodFragment().newInstance();

                return fragment;
            }else{
                return new SetupFragment().newInstance();
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {

            if (position == 0) {
                // if position is zero, set the title to RECEIVED.
                return "Plan";
            } else {
                // if position is 1, set the title to SENT.
                return "Setup";
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

        SharedPreferences prefs = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        //get new weights
        TextView tv = (TextView) setupLayout.findViewById(R.id.bench_max);
        String benchMax =  tv.getText().toString();
        tv = (TextView) setupLayout.findViewById(R.id.squat_max);
        String squatMax =  tv.getText().toString();
        tv = (TextView) setupLayout.findViewById(R.id.deadlift_max);
        String deadLiftMax =  tv.getText().toString();
        //get new increments
        tv = (TextView) setupLayout.findViewById(R.id.deadlift_increment);
        String deadliftIncrement =  tv.getText().toString();
        tv = (TextView) setupLayout.findViewById(R.id.squat_increment);
        String squatIncrement =  tv.getText().toString();
        tv = (TextView) setupLayout.findViewById(R.id.bench_increment);
        String benchIncrement =  tv.getText().toString();

        editor.putString("bench_max",benchMax);
        editor.putString("squat_max",squatMax);
        editor.putString("deadlift_max",deadLiftMax);
        editor.putString("deadlift_increment",deadliftIncrement);
        editor.putString("squat_increment",squatIncrement);
        editor.putString("bench_increment",benchIncrement);
        editor.commit();

        //update the plan
        rebuild();

    }
    public void finishWeek(View v){
        Intent intent = new Intent(this,FinishWeekActivity.class);
        startActivity(intent);

    }

    @Override
    public void onResume(){
        super.onResume();
        rebuild();

    }

    public void rebuild(){
        List<Fragment> fragies =getSupportFragmentManager().getFragments();
        if(fragies!=null){
            for (Fragment fragment : fragies) {
                try {
                    android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.detach(fragment).attach(fragment).commit();

                } catch (NullPointerException e) {

                }
            }

        }
    }
}

