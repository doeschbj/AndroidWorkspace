package com.example.mygame;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/*
* Hauptklasse
* */
public class mainActivity extends AppCompatActivity {

    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        // set our MainGamePanel as the View
        FrameLayout game = new FrameLayout(this);
        MainGamePanel mgp = new MainGamePanel(this);
        RelativeLayout controls = findViewById(R.id.fullscreen_content_controls);
        //setContentView(new MainGamePanel(this));
        game.addView(controls);
        game.addView(mgp);
        setContentView(game);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}
