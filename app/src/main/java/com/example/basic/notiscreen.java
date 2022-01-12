package com.example.basic;

import static com.example.basic.MainActivity.highscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class notiscreen extends AppCompatActivity {
    private Button button;
    private int newScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notiscreen);
        TextView textView = (TextView) findViewById(R.id.notificationDisplay);
        textView.setText(AlarmReceiver.randomStr);
        button = (Button) findViewById(R.id.yes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.highscore = MainActivity.highscore - 20;
                System.out.println(highscore);
                SharedPreferences prefs = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(MainActivity.KEY_HIGHSCORE,MainActivity.highscore);
                editor.apply();

                openHome();

            }
        });
        button = (Button) findViewById(R.id.no);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openHome();

            }
        });


    }
    public void openHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


