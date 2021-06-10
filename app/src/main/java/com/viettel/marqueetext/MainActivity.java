package com.viettel.marqueetext;

import android.content.res.Configuration;
<<<<<<< HEAD
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
=======
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
>>>>>>> 4a53c09df07006b3fbae116c6dac042490349f6c
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
public class MainActivity extends AppCompatActivity {
    private long pressedTime;
    TextRunningTask textRunning;
    Integer counterRunning = 0;
=======
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvMarquee;
    private long pressedTime;

>>>>>>> 4a53c09df07006b3fbae116c6dac042490349f6c

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        Log.d("MainActivity Lifecycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        textRunning = new TextRunningTask(this);
        textRunning.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, counterRunning);
        counterRunning += 1;
=======

        tvMarquee = (TextView) findViewById(R.id.marquee);
        tvMarquee.setSelected(true);
        Log.d("MainActivity Lifecycle", "onCreate");

>>>>>>> 4a53c09df07006b3fbae116c6dac042490349f6c
    }

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back to exit", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }
    }

    @Override
<<<<<<< HEAD
    protected void onStop() {
        super.onStop();
        textRunning.cancel(true);
=======
    protected void onRestart() {
        super.onRestart();
//        Log.d("MainActivity Lifecycle", "onRestart");

        Random random = new Random();
        tvMarquee.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
>>>>>>> 4a53c09df07006b3fbae116c6dac042490349f6c
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
