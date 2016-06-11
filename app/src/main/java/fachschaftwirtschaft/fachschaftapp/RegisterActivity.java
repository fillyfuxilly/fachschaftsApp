
package fachschaftwirtschaft.fachschaftapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import webService.User;

/**
 * @author Matthias Heinen
 */
public class RegisterActivity extends Activity {

    EditText ed1;
    Button b1;
    SharedPreferences sharedpreferences;
    private static final String TAG = "RegisterActivity";
    String n;
    int g;
    NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.d(TAG, "Register Layout aufgebaut");

        ed1=(EditText)findViewById(R.id.editText);

        b1=(Button)findViewById(R.id.button_r);

/*
        final Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
*/
        picker = (NumberPicker) findViewById(R.id.numberPicker);

        picker.setMinValue(1);
        picker.setMaxValue(10);
        picker.setWrapSelectorWheel(false);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Register Button gedrückt");

                sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                n  = ed1.getText().toString();
                g  = picker.getValue();

                new AsyncRegisterNewUser().execute(new User(n, g));

            }
        });
    }
    private class AsyncRegisterNewUser extends AsyncTask<User, Void, String> {
        @Override
        protected String doInBackground(User... params) {

            return ErstiHelferClient.registerNewUser(params[0].getUsername(), params[0].getGroupNr());
        }
        @Override
        protected void onPostExecute(String result) {
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString("nameKey", n);
            editor.putString("gruppeKey", Integer.toString(g));
            editor.apply();

            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_LONG).show();

            Log.d(TAG, "Erfolgreich registriert");

        }
    }
}