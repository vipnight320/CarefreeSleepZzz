package com.example.j329.carefreesleepzzz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class CalculateDialog extends DialogFragment {
    Random random = new Random();
    //四則演算判定
    int operator = random.nextInt(4);
    int ANS = 0;

    int A = 0;
    int B = 0;
    int wrongcount = 0;
    String ope;
    String aho = "三回間違えたね！頭冷やしてきな！ :)";

    @Override
    public Dialog onCreateDialog(Bundle savedInstamceState){

        //なーにがRequiedType：Dialogじゃボケ
        switch (operator){
            case 0:
                int randA = random.nextInt(100);
                int randB = random.nextInt(100);
                ANS = randA + randB;
                A = randA;
                B = randB;
                ope = "+";
                //return A;
            break;
            case 1:
                //Ansが負の数だったらもう一回出力
                while (ANS < 0) {
                    int randA1 = random.nextInt(100);
                    int randB1 = random.nextInt(100);
                    ANS = randA1 - randB1;
                    A = randA1;
                    B = randB1;
                    ope = "-";
                }
                break;
            case 2:
                int randA2 = random.nextInt();
                int randB2 = random.nextInt();
                ANS = randA2 * randB2;
                A = randA2;
                B = randB2;
                ope = "*";
                break;
            case 3:
                //randA Bの商があまりが出たらループ
                int loopfl = 1;
                while(loopfl != 0){
                    int randA3 = random.nextInt(100);
                    int randB3 = random.nextInt(10);
                    ANS = randA3 / randB3;
                    loopfl = randA3 % randB3;
                    A = randA3;
                    B = randB3;
                    ope = "/";
                }
                break;
            default:
                int randerrorA = 73;
                int randerrorB = 22;
                ANS = 95;
                A = randerrorA;
                B = randerrorB;
                ope = "+";
                break;
        }

        final String ANS1 = Integer.toString(ANS);

        final EditText editText = new EditText(getContext());
        editText.setHint("数値を入れてね(半角で！)");

        //ダイアログのビルダーを生成
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //ダイアログのタイトルを設定
        builder.setTitle("ちゃんと起きた？？？");

        /*builder.setMessage(A);
        builder.setMessage(ope);
        builder.setMessage(B);*/
        //builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        //正解するか三回間違えたらアラームストップ
                        while(editText.equals(ANS1) || wrongcount > 3){
                            wrongcount++;
                        }
                        if(wrongcount > 3){
                            builder.setMessage(aho);
                        }
                    }
                })
                .show();

        /*builder.setView(editText);
        builder.setMessage(A);
        builder.setMessage(ope);
        builder.setMessage(B);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        //正解するか三回間違えたらアラームストップ
                        while(editText.equals(ANS1) || wrongcount > 3){
                            wrongcount++;
                        }
                        if(wrongcount > 3){
                            builder.setMessage(aho);
                        }
                    }
                })
                .show();*/
        AlertDialog dialog = builder.create();
        return dialog;
    }
}