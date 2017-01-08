package bro.tm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import models.WeeklyMaxLogsHelper;


public class LogsFragment extends Fragment {

    public static LogsFragment newInstance(){
        LogsFragment thisFragment = new LogsFragment();
        return thisFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.logs_layout , container, false);

        //get the values from database
        WeeklyMaxLogsHelper logs = new WeeklyMaxLogsHelper(this.getActivity());
        SQLiteDatabase db = logs.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "select * from logs",null);
        //draw the graph

        GraphView benchGraph = (GraphView) rootView.findViewById(R.id.bench_graph);
        GraphView squatGraph = (GraphView) rootView.findViewById(R.id.squat_graph);
        GraphView deadliftGraph = (GraphView) rootView.findViewById(R.id.deadlift_graph);

        cursor.moveToFirst();

        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>();

        while(!cursor.isAfterLast()){
            series1.appendData(new DataPoint(cursor.getPosition(),cursor.getDouble(0)),true,6);
            series2.appendData(new DataPoint(cursor.getPosition(),cursor.getDouble(1)),true,6);
            series3.appendData(new DataPoint(cursor.getPosition(),cursor.getDouble(2)),true,6);

            cursor.moveToNext();
        }
        benchGraph.addSeries(series1);
        deadliftGraph.addSeries(series2);
        squatGraph.addSeries(series3);

        benchGraph.getViewport().setScalable(true);
        benchGraph.getViewport().setScrollable(true);
        benchGraph.getViewport().setScalableY(true);
        benchGraph.getViewport().setScrollableY(true);

        squatGraph.getViewport().setScalable(true);
        squatGraph.getViewport().setScrollable(true);
        squatGraph.getViewport().setScalableY(true);
        squatGraph.getViewport().setScrollableY(true);

        deadliftGraph.getViewport().setScalable(true);
        deadliftGraph.getViewport().setScrollable(true);
        deadliftGraph.getViewport().setScalableY(true);
        deadliftGraph.getViewport().setScrollableY(true);
        return rootView;
    }

}