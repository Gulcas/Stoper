package com.example.stoper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.time_view); //pobranie referencji do textview

        /**W celu opóźnienia wykonywanego kodu zastosowano klasę Handler, opóźnienie wykonania zawartego w niej kodu
         * wynosi 1s i bedzie wykonywanie cyklicznie**/
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds/3600; //dzielenie liczby sekund przez liczbę sekund w godzinie
                int minutes = (seconds%3600)/60; //dzielenie pozostałości sekund przez 60sec w minucie
                int secs = seconds%60; //dzielenie pozostałości z pozostałości

                String time = String.format("%d:%02d:%02d", hours, minutes, secs); //zapis danych do string'a
                timeView.setText(time);

                if(running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }
}
