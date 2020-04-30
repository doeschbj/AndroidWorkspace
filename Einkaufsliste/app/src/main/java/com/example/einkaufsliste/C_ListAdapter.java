package com.example.einkaufsliste;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        final ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            Eintrag eintrag = inputs.get(position);
            convertView = mInflater.inflate(mViewResourceId, null);
            if (eintrag != null) {
                CheckBox check = (CheckBox) convertView.findViewById(R.id.check);
                check.setTag(position);
                TextView name = (TextView) convertView.findViewById(R.id.name);
                TextView num = (TextView) convertView.findViewById(R.id.anzahl);
                if (name != null) {
                    name.setText(eintrag.getName());
                }
                if (num != null) {
                    num.setText(eintrag.getNum());
                }
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inputs.get((int)view.getTag()).setSelected();
                    }
                });
            }
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return inputs.size();
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Eintrag getItem(int position) {
        return inputs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        protected CheckBox checkBox;
        private TextView entry;
        private EditText count;
    }

}

