package com.example.j329.carefreesleepzzz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    private SoundPool soundPool;
    private int Alarm;

    @Override
    public void onReceive(Context context, Intent intent) {

            // アラームを受け取って起動するActivityを指定、起動
            Intent notification = new Intent(context, AlarmNortificationActivity.class);
            // 画面起動に必要
            notification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(notification);

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

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool Alarm, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        }); //2/2 このよくわからんonloadについて調べるのとDB＆通知機能 構築 その後set timerについて考える？

        Alarm = soundPool.load(context, R.raw.alarm1, 1); //Alarmに音源を定義する

        soundPool.play(Alarm, 1.0f, 1.0f, 0, 1, 1);//loopの部分をconfigから値を渡せるようにしたい

        // toast で受け取りを確認
        Toast.makeText(context, "Received", Toast.LENGTH_LONG).show();
    }
}