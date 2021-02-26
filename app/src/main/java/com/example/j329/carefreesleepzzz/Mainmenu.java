package com.example.j329.carefreesleepzzz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mainmenu extends AppCompatActivity {

    private SoundPool soundPool;
    private int Alarm;

    //アラームの主キーとなる部分 db用変数名と被りを回避するため_を挿入
    int _alarmId = -1;
    //時間をデータベースに保存するための初期宣言
    /*
    int _hour = 0;
    int _min = 0;

    int sethour;
    int setminute;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //ここから音関連

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                //ストリーム数を指定
                .setMaxStreams(1)
                .build();

        Alarm = soundPool.load(this, R.raw.alarm1, 1); //Alarmに音源を定義する

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool Alarm, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        }); //2/2 このよくわからんonloadについて調べるのとDB＆通知機能 構築 その後set timerについて考える？

        //ここからリスナー関連
        Button sleep = findViewById(R.id.sleep); //寝るボタンが押されたとき
        HelloListener listener = new HelloListener();
        sleep.setOnClickListener(listener);

        Button bt_sleep  = findViewById(R.id.bt_sleep); //ここでスリープボタンに遷移するリスナーを記述
        bt_sleep.setOnClickListener(listener);

        Button bt_wakeup = findViewById(R.id.bt_wakeup);//起きるボタンが押された時の処理
        bt_wakeup.setOnClickListener(listener);

        Button bt_configs = findViewById(R.id.bt_configs);//設定ボタンが押された時の処理
        bt_configs.setOnClickListener(listener);

    }

    private class HelloListener implements View.OnClickListener { //ここでスリープボタン画面遷移のプログラム

        int sleepflag=0;    //寝るボタンと起きるボタンの遷移の判定

        //ここで現在時間を取得
        // 現在日時情報で初期化されたインスタンスの生成
        Date nowtime = new Date();
        SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );
        String display = format.format( nowtime );

        //時の現在時刻を取得
        SimpleDateFormat nh = new SimpleDateFormat( "HH" );
        String nhh = nh.format( nowtime );
        int nowhour = Integer.parseInt(nhh);
/*
        //分の現在時刻を取得
        SimpleDateFormat nm = new SimpleDateFormat( "mm" );
        String nmm = nm.format( nowtime );
        int nowminute = Integer.parseInt(nmm);
*/
        DatabaseHelper helper = new DatabaseHelper(Mainmenu.this);
        SQLiteDatabase db = helper.getReadableDatabase();

        @Override
        public void onClick(View view) {
            int id = view.getId();
/* db封印 どうせ使わんし
            try {
                //ここにsethour,minuteを呼ぶためのdbのコードを書く
                String sql = "SELECT * FROM alarmdata WHERE hour = " + _alarmId;
                Cursor cursor = db.rawQuery(sql, null);

                //教科書p247参考
                //cursor.moveToNext();
                while (cursor.moveToNext()){
                    int idxNote = cursor.getColumnIndex("hour");
                    int idxNote1 = cursor.getColumnIndex("min");
                    //int sethour;
                    //int setminute;
                    sethour = cursor.getInt(idxNote);
                    setminute = cursor.getInt(idxNote1);
                }
            }finally{
                db.close();
            }*/

            switch (id) {
                case R.id.sleep:
                    /*sleepflagが0なら、おやすみ。1ならおはよう*/
                    if(sleepflag==0){
                        //String show = "今から寝るよ！" + System.lineSeparator() + "明日起きる時間は："+  +";
                        String show = "今から寝るよ！" + System.lineSeparator() + "明日起きる時間は："+ nowhour+7 +"です！";
                        String show2 = "null値ですエラー";//null値を設定してるため
                        Toast.makeText(Mainmenu.this, show, Toast.LENGTH_SHORT).show();
                        TextView output = findViewById(R.id.sleep);
                        output.setText("起きる");
                        sleepflag++;
                        if(soundPool == null){
                            Toast.makeText(Mainmenu.this, show2, Toast.LENGTH_SHORT).show();
                        }

                            //CalculateDialog dialog = new CalculateDialog();
                            //dialog.show(getSupportFragmentManager(),"Calculate");
                            soundPool.play(Alarm, 1.0f, 1.0f, 0, 1, 1);//loopの部分をconfigから値を渡せるようにしたい //最悪プレビュー用としてここに置いとく
                            Alarm = soundPool.load(Mainmenu.this, R.raw.alarm1, 1); //Alarmに音源を定義する
                    }else if(sleepflag==1) {
                        soundPool.autoPause();

                        String show = "おはようございます！！" + System.lineSeparator() + "現在時刻は：" + display + "です！";
                        Toast.makeText(Mainmenu.this, show, Toast.LENGTH_LONG).show();
                        TextView output = findViewById(R.id.sleep);
                        output.setText("寝る！！！");
                        sleepflag--;
                    }
                    break;
                case R.id.bt_sleep:
                    finish();
                    Intent intent = new Intent(Mainmenu.this, Sleepchecker.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent);
                    break;
                case R.id.bt_wakeup:
                    Intent intent2 = new Intent(Mainmenu.this, notWakeup.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent2);
                    break;
                case R.id.bt_configs:
                    Intent intent3 = new Intent(Mainmenu.this, Configs.class); //１個目は遷移元、２個目は遷移先
                    startActivity(intent3);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + id);
            }
    }
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
