package com.example.j329.carefreesleepzzz;

import android.content.Intent;
import android.app.Service;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Pleaseworksomething extends Service {
    //これがないとエラーになる（おまじない的な感じ）
    /*public IBinder onBind(Intent intent) {
        return null;
    }*/

    private SoundPool soundPool;
    private int Alarm;

    @Override
    public void onCreate() {

        Thread thread = new Thread(null, task, "sentMessageService");
        thread.start();
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
                Log.d("debug", "sampleId=" + sampleId);
                Log.d("debug", "status=" + status);
            }
        });

        Alarm = soundPool.load(this, R.raw.alarm1, 1); //Alarmに音源を定義する

        soundPool.play(Alarm, 1.0f, 1.0f, 0, 1, 1);//loopの部分をconfigから値を渡せるようにしたい

        // toast で受け取りを確認
        Toast.makeText(this, "Received", Toast.LENGTH_LONG).show();

    }
}
