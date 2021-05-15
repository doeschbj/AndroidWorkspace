package com.example.lovecounter;

import android.Manifest;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BackGroundThread{
    private boolean run_ = true;
    private ConstraintLayout back_lay;
    private ArrayList<BackgroundHolder> draw_array = new ArrayList<BackgroundHolder>();
    private int sw = 0;
    public Context context;
    public boolean is_init = false;

    // File representing the folder that you select using a FileChooser

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp" , "jpg"// and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    static final File dir = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() +"/WIOAPP/" );


    public BackGroundThread(Context con){

        LoadThread l_th = new LoadThread();
        l_th.start();
    }


    public BackGroundThread(ConstraintLayout lay){
        this.back_lay = lay;
    }

    public Drawable getImageDr(Context con){
        if(this.is_init){
            int random = new Random().nextInt(draw_array.size()-1);
            return draw_array.get(random).getBckgr();
        }else{
            return AppCompatResources.getDrawable(con, R.drawable.background_1);
        }
    }

    public void setConlayout(ConstraintLayout lay){
        this.back_lay = lay;
    }

    private class LoadThread extends Thread{
        @Override
        public void run() {
            if (dir.isDirectory()) { // make sure it's a directory
                ExifInterface exif;
                String date = "";
                for (final File f : Objects.requireNonNull(dir.listFiles(IMAGE_FILTER))) {
                    try {
                        exif = new ExifInterface(f.getPath());
                        date =  exif.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    draw_array.add(new BackgroundHolder(Drawable.createFromPath(f.getPath()),date));
                }
                is_init = true;
            }

        }
    }
}
