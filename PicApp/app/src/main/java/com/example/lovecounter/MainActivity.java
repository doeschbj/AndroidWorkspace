package com.example.lovecounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    private String days = "days";
    public final String[] EXTERNAL_PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE};
    public final int EXTERNAL_REQUEST = 138;
    public BackGroundThread back_thread;
    public ConstraintLayout back_constraint;
    public LocalDate thedate = LocalDate.of(2021,4,11);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestForPermission();
        back_thread = new BackGroundThread(getApplicationContext());
        setContentView(R.layout.activity_main);

        TextView date_tv = findViewById(R.id.date_);

        ZoneId zonedId = ZoneId.of( "Europe/Berlin" );
        LocalDate today = LocalDate.now( zonedId );
        Period period = Period.between(thedate, today);
        int dayselapsed = period.getDays();
        int month = period.getMonths();

        date_tv.setText(dayselapsed + " " + days);

        back_constraint = findViewById(R.id.background_layout);
        back_thread.setConlayout(back_constraint);

        Timer timer = new Timer();
        MyTimer myTimer = new MyTimer();
        timer.schedule(myTimer, 0,3000);
    }


    private class MyTimer extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    back_constraint.setBackground(back_thread.getImageDr(getBaseContext()));
                }
            });
        }
    }

    public boolean requestForPermission() {

        boolean isPermissionOn = true;
        isPermissionOn = false;
        requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST);
        return isPermissionOn;
    }

    private boolean hasPermission(String perm) {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, perm));

    }
}