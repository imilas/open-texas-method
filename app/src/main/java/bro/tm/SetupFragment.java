package bro.tm;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.DashPathEffect;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;

import models.WeeklyMaxLogsHelper;


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

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.setup_layout , container, false);
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



        //get the values from database
        WeeklyMaxLogsHelper logs = new WeeklyMaxLogsHelper(this.getActivity());
        SQLiteDatabase db = logs.getReadableDatabase();

        Cursor cursor = db.rawQuery(
        "select * from logs",null);
        //draw the graph

        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);

        cursor.moveToFirst();


        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>();

        while(!cursor.isAfterLast()){
            series1.appendData(new DataPoint(cursor.getPosition(),cursor.getDouble(0)),true,6);
            series2.appendData(new DataPoint(cursor.getPosition(),cursor.getDouble(1)),true,6);
            series3.appendData(new DataPoint(cursor.getPosition(),cursor.getDouble(2)),true,6);

            cursor.moveToNext();
        }
        graph.addSeries(series1);
        graph.addSeries(series2);
        graph.addSeries(series3);


        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

// activate horizontal scrolling
        graph.getViewport().setScrollable(true);

// activate horizontal and vertical zooming and scrolling
        graph.getViewport().setScalableY(true);

// activate vertical scrolling
        graph.getViewport().setScrollableY(true);




        //return root

        return rootView;
    }

}
