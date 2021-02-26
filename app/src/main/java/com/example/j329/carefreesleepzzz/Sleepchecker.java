package com.example.j329.carefreesleepzzz;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Sleepchecker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepchecker);

            Sleepchecker.HelloListener listener = new Sleepchecker.HelloListener();

            Button bfcp = findViewById(R.id.buckfromincursedprogram); //ここでスリープボタンに遷移するリスナーを記述
            bfcp.setOnClickListener(listener);

        /*
        Button bt_sleep = findViewById(R.id.bt_sleep); //ここでスリープボタンに遷移するリスナーを記述
        bt_sleep.setOnClickListener(listener);

        Button bt_mainmenu = findViewById(R.id.bt_mainmenu);//起きるボタンが押された時の処理
        bt_mainmenu.setOnClickListener(listener);

        Button bt_wakeup = findViewById(R.id.bt_wakeup);//目覚ましボタンが押された時の処理
        bt_wakeup.setOnClickListener(listener);*/

        }
        private class HelloListener implements View.OnClickListener { //ここでスリープボタン画面遷移のプログラム

            @Override
            public void onClick(View view) {

                int id = view.getId();

                switch (id) {
                    case R.id.buckfromincursedprogram:
                        finish();
                        break;
                /*case R.id.bt_mainmenu:
                    Intent intent7 = new Intent(Configs.this, Mainmenu.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent7);
                    break;
                case R.id.bt_sleep:
                    Intent intent8 = new Intent(Configs.this, Sleepchecker.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent8);
                    break;
                case R.id.bt_wakeup:
                    Intent intent9 = new Intent(Configs.this, Wakeup.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent9);
                    break;*/
                }
            }
            // my_child_toolbar is defined in the layout file
        /*Toolbar myChildToolbar = findViewById(R.id.getback);
        setSupportActionBar(myChildToolbar);

        ActionBar actionBar = getSupportActionBar(); //アクションバーを取得
        actionBar.setDisplayHomeAsUpEnabled(true); //アクションバーの戻るメニューを有効化*/
        }
        protected void onDestroy(){
            super.onDestroy();
        }
    }

        /*

/* いったん動作確認のため封印
        mChart = findViewById(R.id.line_chart);

        // Grid背景色
        mChart.setDrawGridBackground(true);

        // no description text
        mChart.getDescription().setEnabled(true);

        // Grid縦軸を破線
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        // Y軸最大最小設定
        leftAxis.setAxisMaximum(150f);
        leftAxis.setAxisMinimum(0f);
        // Grid横軸を破線
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

        // 右側の目盛り
        mChart.getAxisRight().setEnabled(false);

        // add data
        setData();

        mChart.animateX(2500);
        //mChart.invalidate();

        // dont forget to refresh the drawing
        // mChart.invalidate();
        */

        /*Sleepchecker.HelloListener listener = new Sleepchecker.HelloListener();

        Button bt_mainmenu = findViewById(R.id.bt_mainmenu);//メニューボタンが押された時の処理
        bt_mainmenu.setOnClickListener(listener);

        Button bt_wakeup = findViewById(R.id.bt_wakeup);//目覚ましボタンが押された時の処理
        bt_wakeup.setOnClickListener(listener);

        Button bt_configs = findViewById(R.id.bt_configs);//設定ボタンが押された時の処理
        bt_configs.setOnClickListener(listener);

    }*/
    /*  起きた時間のdb
    参考:https://qiita.com/kengo_kuwahara/items/a8ef858a9810cad42ca6
     */
    /*public class TestOpenHelper extends SQLiteOpenHelper {

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
                        COLUMN_NAME_SUBTITLE + " INTEGER)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;


        TestOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    SQL_CREATE_ENTRIES
            );

            saveData(db, "music1", 10);

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

        public void saveData(SQLiteDatabase db, String title, int score){
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("score", score);

            db.insert("sleeplog", null, values);
        }
    }*/
    /*private void setData() {
        // Entry()を使ってLineDataSetに設定できる形に変更してarrayを新しく作成
        int data[] = {116, 111, 112, 121, 102, 83,
                99, 101, 74, 105, 120, 112,
                109, 102, 107, 93, 82, 99, 110,
        };

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            values.add(new Entry(i, data[i], null, null));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {

            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet");

            set1.setDrawIcons(false);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(0f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set1.setFillColor(Color.BLUE);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData lineData = new LineData(dataSets);

            // set data
            mChart.setData(lineData);
        }ここまで封印*/
/*    private class HelloListener implements View.OnClickListener { //ここでスリープボタン画面遷移のプログラム

        @Override
        public void onClick(View view) {

            int id = view.getId();

            switch (id) {
                case R.id.bt_mainmenu:
                    finish();
                    break;
                case R.id.bt_wakeup:
                    finish();
                    Intent intent5 = new Intent(Sleepchecker.this, Wakeup.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent5);
                    break;
                case R.id.bt_configs:
                    finish();
                    Intent intent6 = new Intent(Sleepchecker.this, Configs.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent6);
                    break;
            }
        }
    }*/
    /*ActionBar actionBar = getSupportActionBar(); //アクションバーを取得
    actionBar.setDisplayHomeAsUpEnabled(true);*///アクションバーの戻るメニューを有効化
    //}
    /*戻るボタンの中身*/
    /*@Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId(); //ここでボタン用のmenuIDを取得

        if (itemID == android.R.id.home){ //選択されたメニューが戻るの場合、アクティビティを終了
          finish();
        }
        return super.onOptionsItemSelected(item);
    }*/
//}
