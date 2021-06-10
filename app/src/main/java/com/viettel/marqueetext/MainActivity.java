package com.viettel.marqueetext;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private long pressedTime;
    TextRunningTask textRunning;
    Integer counterRunning = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity Lifecycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        textRunning = new TextRunningTask(this);
        textRunning.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, counterRunning);
        counterRunning += 1;
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
    protected void onStop() {
        super.onStop();
        textRunning.cancel(true);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
