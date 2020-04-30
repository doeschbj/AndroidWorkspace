package com.example.einkaufsliste;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MyDialog extends AppCompatDialogFragment {
    private EditText editname;
    private EditText editanzahl;
    private MyDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(view)
                .setTitle("Hinzufügen")
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = editname.getText().toString();
                        String anzahl = editanzahl.getText().toString();
                        listener.addEntry(name,anzahl);
                    }
                })
                .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MyDialog.this.getDialog().cancel();
                    }
                });
        editname = view.findViewById(R.id.edit_name);
        editanzahl = view.findViewById(R.id.edit_anzahl);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (MyDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement MyDialogListener");
        }
    }

    public interface MyDialogListener{
        void addEntry(String name, String anzahl);
    }

}
