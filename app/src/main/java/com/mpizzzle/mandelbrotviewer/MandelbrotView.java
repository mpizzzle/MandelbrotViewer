package com.mpizzzle.mandelbrotviewer;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by mpizzzle on 30/01/18.
 */

public class MandelbrotView extends View {
    private Complex[][] zValues;
    private int[] colors;
    private int width;
    private int height;
    private int loop;
    private double zoom;
    private ObjectAnimator animator;
    private Paint[] palette;
    private Bitmap bmp;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.5f;



    public MandelbrotView(Context context) {
        super(context);
    }

    public MandelbrotView(Context context, AttributeSet attrs) {
        super(context);
        width = 1080;
        height = 1920;
        zValues = new Complex[width][height];
        colors = new int[width * height];
        loop = 20;
        palette = new Paint[loop];
        zoom = 500d;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());


        for (int i = 0; i < loop; ++i)
        {
            palette[i] = new Paint();
            palette[i].setARGB(255, ((i + 1) * (255 / loop)), 255 - ((i + 1) * (255 / loop)), 0);
        }

        palette[loop - 1].setColor(Color.BLACK);

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                zValues[i][j] = new Complex(0d, 0d);
                colors[(i * width) + j] = Color.BLACK;
            }
        }

        for (int k = 0; k < loop; ++k) {
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    zFunction(zValues[i][j].getNatural(), zValues[i][j].getImaginary(), (j - (height / 2d)) / zoom, (i - (width / 2d)) / zoom, i, j);
                    if (Math.sqrt(Math.pow(zValues[i][j].getNatural(), 2) + Math.pow(zValues[i][j].getImaginary(), 2)) <= 2) {
                        colors[(i * width) + j] = palette[k].getColor();
                    }
                }
            }
        }

        bmp = Bitmap.createBitmap (colors, width, height, Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 0);
        Matrix m = new Matrix();
        m.postRotate(90);
        m.postTranslate(width, 0);
        canvas.drawBitmap(bmp, m, new Paint());
        m.postTranslate(0, width);
        canvas.drawBitmap(bmp, m, new Paint());
    }

    public void setNextStep(int value){
        invalidate();
    }

    public ObjectAnimator getAnimator() {
        return animator;
    }

    public void setAnimator(ObjectAnimator animator) {
        this.animator = animator;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }


    public void zFunction(double zn, double zi, double cn, double ci, int x, int y) {
        zValues[x][y].setNatural(zn * zn - zi * zi + cn);
        zValues[x][y].setImaginary(2 * zn * zi + ci);
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }
}
