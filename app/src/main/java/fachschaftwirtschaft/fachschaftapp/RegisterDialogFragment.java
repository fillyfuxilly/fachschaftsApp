package fachschaftwirtschaft.fachschaftapp;

import android.app.AlertDialog;
import android.app.Dialog;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

/**
 * Created by matthias on 11.05.16.
 */
public class RegisterDialogFragment extends DialogFragment {

    SharedPreferences sharedpreferences;
    EditText ed1,ed2;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_register, null))

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                     /*   ed1=(EditText)findViewById(R.id.editText);
                        ed2=(EditText)findViewById(R.id.editText2);
                        sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                        String n  = ed1.getText().toString();
                        String g  = ed2.getText().toString();

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString("nameKey", n);
                        editor.putString("gruppeKey", g);
                        editor.apply(); */
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RegisterDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
