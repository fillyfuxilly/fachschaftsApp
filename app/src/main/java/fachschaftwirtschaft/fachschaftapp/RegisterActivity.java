
package fachschaftwirtschaft.fachschaftapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import webService.User;

/**
 * @author Matthias Heinen
 */
public class RegisterActivity extends AppCompatActivity {

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


                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new AsyncRegisterNewUser().execute(new User(n, g));
                } else {
                    Log.d(TAG, "Keine Internetverbindung");
                    Toast.makeText(RegisterActivity.this, "Verbindung zum Internet benötigt", Toast.LENGTH_LONG).show();
                }



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