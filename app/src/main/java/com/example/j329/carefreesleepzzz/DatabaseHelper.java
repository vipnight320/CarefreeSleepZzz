package com.example.j329.carefreesleepzzz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
   //データベース名
    private static final String DATABASE_NAME = "alarmdata.db";
    private static final int DATABASE_VERSION = 3;

    //コンストラクタ
    public DatabaseHelper(Context context){
        //親クラスのコンストラクタの呼び出し
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        //ここでAlarmのDBテーブルを生成
        sb.append("CREATE TABLE alarmdata(");
        sb.append("_id INTEGER PRIMARY KEY,");
        sb.append("hour INTEGER,");
        sb.append("min INTEGER");
        sb.append(");");
        String sql = sb.toString();

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
    }
}