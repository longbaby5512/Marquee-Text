package com.viettel.marqueetext;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Random;

public class TextRunningTask extends AsyncTask<Integer, Void, Void> {
    WeakReference<Activity> contextParent;
    Animation animationText;
    WeakReference<TextView> tvMarquee;
    Integer countHome = 0;

    public TextRunningTask(Activity contextParent) {
        super();
        this.contextParent = new WeakReference<>(contextParent);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        animationText = AnimationUtils.loadAnimation(contextParent.get(), R.anim.translate);
        animationText.reset();
        tvMarquee = new WeakReference<>((TextView) contextParent.get().findViewById(R.id.marquee));
    }

    @Override
    protected Void doInBackground(@NonNull Integer... integers) {
        Log.d("Check Thread", "Run");
        countHome = integers[0];
        publishProgress();
        return null;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        tvMarquee.get().startAnimation(animationText);
        Random random = new Random();
        if (countHome != 0) {
            tvMarquee.get().setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        }
    }


}
