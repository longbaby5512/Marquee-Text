package com.viettel.marqueetext;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private long pressedTime;
    private static int counterPressedHome = 0;
    private LinearLayout drawTextCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity Lifecycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        final SurfaceViewThread surfaceViewThread = new SurfaceViewThread(getApplicationContext(), counterPressedHome);
        counterPressedHome++;

        drawTextCanvas = (LinearLayout) findViewById(R.id.drawTextCanvas);
        drawTextCanvas.destroyDrawingCache();
        drawTextCanvas.addView(surfaceViewThread);
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
        drawTextCanvas.removeAllViews();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
