package com.mpizzzle.mandelbrotviewer;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mpizzzle on 30/01/18.
 */

public class MandelbrotView extends View {
    private Complex[][] zValues;
    private boolean[][] ignore;
    private Paint black;
    private Paint white;
    private int width;
    private int height;
    private ObjectAnimator animator;

    public MandelbrotView(Context context) {
        super(context);
    }

    public MandelbrotView(Context context, AttributeSet attrs) {
        super(context);
        black = new Paint(Color.BLACK);
        white = new Paint(Color.WHITE);
        width = 1080;
        height = 1920;
        zValues = new Complex[width][height];
        ignore = new boolean[width][height];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                zValues[i][j] = new Complex(0d, 0d);
                ignore[i][j] = false;
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 255, 0);
        //for (int loop = 0; loop < 50; ++loop) {
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    zFunction(zValues[i][j].getNatural(), zValues[i][j].getImaginary(), (i - (width / 2d)) / 200d, (j - (height / 2d)) / 200d, i, j);
                    if (Math.sqrt(Math.pow(zValues[i][j].getNatural(), 2) + Math.pow(zValues[i][j].getImaginary(), 2)) <= 2) {
                        canvas.drawPoint(i, j, black);
                    }
                }
            }
        //}

        /*for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (Math.sqrt(Math.pow(zValues[i][j].getNatural(), 2) + Math.pow(zValues[i][j].getImaginary(), 2)) <= 2) {
                    canvas.drawPoint(i, j, black);
                }
            }
        }*/
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
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    public void zFunction(double zn, double zi, double cn, double ci, int x, int y) {
        //double n = zn * zn - zi * zi + c.getNatural();
        //double i = 2 * zn * zi + c.getImaginary();
        zValues[x][y].setNatural(zn * zn - zi * zi + cn);
        zValues[x][y].setImaginary(2 * zn * zi + ci);
    }
}
