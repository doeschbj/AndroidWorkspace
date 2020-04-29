package com.example.einkaufsliste;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Eintrag> data;
    DataBaseHolder db;
    Cursor dbdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello There", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ListView list = (ListView) findViewById(R.id.view_list);


        data = new ArrayList<Eintrag>();
        db = new DataBaseHolder(this);


        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));
        data.add(new Eintrag("Mehl","500"));

        C_ListAdapter adapter = new C_ListAdapter(this, R.layout.rowlayout, data);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
