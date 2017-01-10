package models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 29/12/16.
 */

public class WeeklyMaxLogsHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 6;
        private static final String LOGS_TABLE_NAME = "logs";
        private static final String DATABASE_NAME = "TMDB";
        private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + LOGS_TABLE_NAME + " (" +
                        "bench" + " Double, " +
                        "deadlift" + " Double, " +
                        "squat" + " Double);";

        public WeeklyMaxLogsHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DICTIONARY_TABLE_CREATE);
        }


        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL("DROP TABLE IF EXISTS " + LOGS_TABLE_NAME);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
}
