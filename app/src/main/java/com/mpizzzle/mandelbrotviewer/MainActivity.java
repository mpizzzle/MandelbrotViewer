package com.mpizzzle.mandelbrotviewer;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MandelbrotView mandelbrotViewer = findViewById(R.id.mandelbrot);
        //ObjectAnimator animation = ObjectAnimator.ofInt(mandelbrotViewer, "nextStep", 0, 0);
        //animation.setRepeatCount(ObjectAnimator.INFINITE);
        //mandelbrotViewer.setAnimator(animation);

        //animation.start();
    }
}