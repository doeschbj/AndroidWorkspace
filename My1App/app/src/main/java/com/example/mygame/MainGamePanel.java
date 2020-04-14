package com.example.mygame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.mygame.MainThread;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private Paint paint;
    private static final String TAG = MainThread.class.getSimpleName();
    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        paint = new Paint();
        // create the game loop thread
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getY() > getHeight() - 50) {
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    public void update(){

    }

    public void render(Canvas canvas){
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth()/2, getHeight() , paint);
    }
}