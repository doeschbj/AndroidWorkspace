package com.example.einkaufsliste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class C_ListAdapter extends ArrayAdapter<Eintrag> {
    private LayoutInflater mInflater;
    private ArrayList<Eintrag> inputs;
    private int mViewResourceId;
    public C_ListAdapter(Context context, int textViewResourceId, ArrayList<Eintrag> inputs){
        super(context, textViewResourceId, inputs);
        this.inputs = inputs;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Eintrag eintrag = inputs.get(position);

        if (eintrag != null) {
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView num = (TextView) convertView.findViewById(R.id.anzahl);
            if (name != null) {
                name.setText(eintrag.getName());
            }
            if (num != null) {
                num.setText(eintrag.getNum());
            }
        }

        return convertView;
    }
}