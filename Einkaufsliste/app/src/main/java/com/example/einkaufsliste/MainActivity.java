package com.example.einkaufsliste;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyDialog.MyDialogListener {
    ArrayList<Eintrag> data;
    private static final String TAG = "MyActivity";
    DataBaseHolder db;
    C_ListAdapter adapter;
    ListView list;
    Cursor dbdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        list = (ListView) findViewById(R.id.view_list);
        data = new ArrayList<Eintrag>();
        try {
            db = new DataBaseHolder(this);
        }catch (ClassNotFoundException| SQLException e){
            Log.e("MainActivity","error 1"+ e);
        }
        if(db != null){
            data = db.getData();
        }

        data.add(new Eintrag("Mehl","0"));
        data.add(new Eintrag("Mehl","1"));
        data.add(new Eintrag("Mehl","2"));
        data.add(new Eintrag("Mehl","3"));
        data.add(new Eintrag("Mehl","4"));
        data.add(new Eintrag("Mehl","5"));
        data.add(new Eintrag("Mehl","6"));
        data.add(new Eintrag("Mehl","7"));

        adapter = new C_ListAdapter(this, R.layout.rowlayout, data);
        list.setAdapter(adapter);
        btn_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i = data.size() - 1; i >=0 ;i--){
                    if(data.get(i).getSelected()){
                        db_remove(data.get(i));
                        if(data.size()>1){
                            data.remove(i);

                        }else{
                            data.get(0).setName("Nothing here");
                            data.get(0).setNum("");
                            data.get(0).setSelected(false);
                        }

                    }
                }
                list.setAdapter(adapter);
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();
            }
        });


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

    public void openDialog(){
        MyDialog mydialog = new MyDialog();
        mydialog.show(getSupportFragmentManager(),"exampleDialog");
    }

    public void addEntryDB(Eintrag entry){
        //db.addEntry(entry.getName(),entry.getNum());
    }

    public void db_remove(Eintrag entry){
        //db.removeEntry(entry.getName());
    }

    @Override
    public void addEntry(String name, String anzahl) {
        if(name.length() != 0 && anzahl.length() != 0){
            if(data.size() == 1){
                data.remove(0);
            }
            Eintrag entry = new Eintrag(name,anzahl);
            data.add(entry);
            list.setAdapter(adapter);
            addEntryDB(entry);
        }else{
            Toast.makeText(MainActivity.this,"You must enter a text!",Toast.LENGTH_LONG).show();
        }

    }

}
