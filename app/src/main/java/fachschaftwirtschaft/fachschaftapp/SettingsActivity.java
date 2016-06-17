
package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author Matthias Heinen
 */
public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);

        EditText name = (EditText) findViewById(R.id.settingsEditText);
        name.setText(sharedpreferences.getString("nameKey", ""));

        EditText gruppe = (EditText) findViewById(R.id.settingsEditText2);
        gruppe.setText(sharedpreferences.getString("gruppeKey", ""));

    }

    public void doShit(View button) {

         new AsyncRe().execute();
    }

    private class AsyncRe extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            ErstiHelferClient.getAppointments(3, 3);
            return null;
        }

    }
}
