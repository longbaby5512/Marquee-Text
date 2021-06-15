package com.viettel.marqueetext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class SurfaceViewThread extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private final static String LOG_TAG = "SURFACE_VIEW_THREAD";
    private final SurfaceHolder surfaceHolder;
    private final Paint paint;
    private final int counterPressedHome;
    private final String text = "Viettel Cyber Security";
    private final float textWidth;
    private boolean threadRunning = false;
    private int textX;
    private int textY;
    private int screenWidth;
    private int screenHeight;


    public SurfaceViewThread(Context context, int counterPressedHome) {
        super(context);
        setFocusable(true);

        this.counterPressedHome = counterPressedHome;
        surfaceHolder = getHolder();

        surfaceHolder.addCallback(this);

        paint = new Paint();
        paint.setTextSize(100);
        int color;
        if (counterPressedHome == 0) {
            color = Color.parseColor("#EA1C2D");
        } else {
            Random random = new Random();
            color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
        paint.setColor(color);

        Log.w("PRESS_HOME", String.valueOf(counterPressedHome));
        screenHeight = getHeight();
        screenWidth = getWidth();

        setZOrderOnTop(true);
        textWidth = paint.measureText(text, 0, text.length());

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Thread thread = new Thread(this);
        thread.start();
        threadRunning = true;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        threadRunning = false;
    }


    @Override
    public void run() {
        Log.w("SCREEN", "Width: " + screenWidth + " Height: " + screenHeight);
        while (threadRunning) {
            textX += 100;

            screenHeight = getHeight();
            screenWidth = getWidth();
            textY = screenHeight / 2;
            if (textX > screenWidth) {
                textX = (int) -textWidth;
            }
            Log.d("Axis", "X: " + textX + " Y: " + textY);

            drawText();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        }

    }

    private void drawText() {

        int margin = 0;
        int left = 0;
        int right = screenWidth - margin;
        int top = 0;
        int bottom = screenHeight - margin;

        Rect rect = new Rect(left, top, right, bottom);

        Canvas canvas = surfaceHolder.lockCanvas(rect);

        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);

        canvas.drawRect(rect, backgroundPaint);
        canvas.drawText(text, textX, textY, paint);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }

}
