
package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import webService.User;

/** Erlaubt dem Nutzer seine Gruppe zu wechseln.
 * @author Matthias Heinen
 */
public class SettingsActivity extends BaseActivity {
    /**
     * Knopf zum speichern.
     */
    Button btn;
    /**
     * Zur Auswahl der neuen Gruppennummer.
     */
    NumberPicker picker;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);

        TextView name = (TextView) findViewById(R.id.settings_textView2);
        name.setText(sharedpreferences.getString("nameKey", ""));

        TextView group = (TextView) findViewById(R.id.settings_textView4);
        group.setText(sharedpreferences.getString("groupKey", ""));

        picker = (NumberPicker) findViewById(R.id.settings_numberPicker);

        try {
            picker.setMinValue(1);
            picker.setMaxValue(10);
            picker.setWrapSelectorWheel(false);
        } catch(NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(SettingsActivity.this, "Etwas ist schief gelaufen", Toast.LENGTH_LONG).show();
            recreate();
        }


        btn = (Button) findViewById(R.id.settings_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    User currentUser = new User(sharedpreferences.getString("nameKey", ""), picker.getValue());

                    new AsyncChangeGroup().execute(currentUser);

                } else {

                    Toast.makeText(SettingsActivity.this, "Verbindung zum Internet benötigt", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    /**
     * AsyncTask der die Methode changeGroup auf dem Web Service aufruft.
     */
    private class AsyncChangeGroup extends AsyncTask<User, Void, String> {
        @Override
        protected String doInBackground(User... params) {

            return ErstiHelferClient.changeGroup(params[0].getUsername(), params[0].getGroupNr());
        }
        @Override
        protected void onPostExecute(String result) {

            if(result.equals("Gruppe erfolgreich gewechselt")) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("groupKey", Integer.toString(picker.getValue()));

                editor.apply();

            }

            Toast.makeText(SettingsActivity.this, result, Toast.LENGTH_LONG).show();
            recreate();
        }

    }
}
