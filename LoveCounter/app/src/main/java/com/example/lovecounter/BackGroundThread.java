package com.example.lovecounter;

import android.Manifest;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BackGroundThread{
    private boolean run_ = true;
    private ConstraintLayout back_lay;
    private ArrayList<Drawable> draw_array = new ArrayList<Drawable>();
    private int sw = 0;
    public Context context;

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
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : Objects.requireNonNull(dir.listFiles(IMAGE_FILTER))) {
                draw_array.add(Drawable.createFromPath(f.getPath()));
            }
        }

        //draw_array.add(AppCompatResources.getDrawable(con, R.drawable.background_1));
        //draw_array.add(AppCompatResources.getDrawable(con, R.drawable.background_2));
        //draw_array.add(AppCompatResources.getDrawable(con, R.drawable.background_3));
    }


    public BackGroundThread(ConstraintLayout lay){
        this.back_lay = lay;
    }

    public Drawable getImageDr(Context con){
        int random = new Random().nextInt(draw_array.size()-1);
        return draw_array.get(random);
    }

    public void setConlayout(ConstraintLayout lay){
        this.back_lay = lay;
    }
}
