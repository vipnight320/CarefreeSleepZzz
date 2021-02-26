package com.example.j329.carefreesleepzzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*  起きた時間のdb
参考:https://qiita.com/kengo_kuwahara/items/a8ef858a9810cad42ca6
 */
public class SleeplogMaker extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 3;

    // データーベース情報を変数に格納
    private static final String DATABASE_NAME = "Sleeplog.db";
    private static final String TABLE_NAME = "sleeplog";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "lognum";
    private static final String COLUMN_NAME_SUBTITLE = "time";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " TEXT)"; //ここはTEXTから別の型に変更する予定

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    SleeplogMaker(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        saveData(db, "1", 1);

    }

    // 参考：https://sankame.github.io/blog/2017-09-05-android_sqlite_db_upgrade/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveData(SQLiteDatabase db, String lognum, int time) {
        ContentValues values = new ContentValues();
        values.put("lognum", lognum);
        values.put("time", time);

        db.insert("sleeplog", null, values);
    }
}