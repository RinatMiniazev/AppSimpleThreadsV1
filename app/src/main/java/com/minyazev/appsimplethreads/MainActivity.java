package com.minyazev.appsimplethreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView timerTextView = findViewById(R.id.timerTextView);

        // Запускаем дочерний поток
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Задержка 1 секунда
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                    // Обновляем UI с помощью метода post
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timerTextView.setText(String.valueOf(counter));
                        }
                    });
                }
            }
        }).start();
    }
}