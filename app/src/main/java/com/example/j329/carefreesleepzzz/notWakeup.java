package com.example.j329.carefreesleepzzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class notWakeup extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wakeup);

        notWakeup.HelloListener listener = new notWakeup.HelloListener();

        Button bfcp = findViewById(R.id.buckfromincursedprogram); //ここでスリープボタンに遷移するリスナーを記述
        bfcp.setOnClickListener(listener);

    }
    private class HelloListener implements View.OnClickListener { //ここでスリープボタン画面遷移のプログラム

        @Override
        public void onClick(View view) {

            int id = view.getId();

            switch (id) {
                case R.id.buckfromincursedprogram:
                    finish();
                    break;
            }
        }
    }

    protected void onDestroy(){
        super.onDestroy();
    }
}
