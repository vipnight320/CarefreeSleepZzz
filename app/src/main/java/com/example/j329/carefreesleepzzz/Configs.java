package com.example.j329.carefreesleepzzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Configs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configs);  

        Configs.HelloListener listener = new Configs.HelloListener();

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