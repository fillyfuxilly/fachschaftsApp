package fachschaftwirtschaft.fachschaftapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Startseite der App
 * @author Matthias Heinen
 */
public class MainActivity extends BaseActivity {

    Button btn;
    /**
     * Konstante zum loggen.
     */
    private static final String TAG = "MainActivity";
    /**
     * Lokaler Speicher zum Ablegen von Nutzername und Gruppennummer des Nutzers.
     */
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Layout geladen");


        btn = (Button) findViewById(R.id.main_button_clear);

        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sharedpreferences.edit();
                e.clear();
                e.apply();

                Toast.makeText(MainActivity.this,"Daten gelöscht", Toast.LENGTH_LONG).show();
                recreate();

            }
        });



        /**
         * AsnyTask der prueft, ob der User sich bereits registriert hat. Falls nein, dann wird der RegisterActivity Dialog gestartet.
         */
        class RegisteredTask extends AsyncTask<Void,Void,Boolean> {

            @Override
            protected Boolean doInBackground(Void... params){

                SharedPreferences sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                String name = sharedpreferences.getString("nameKey", "");

               return name.equals("");

            }

            @Override
            protected void onPostExecute(Boolean result) {

                if(!result) {

                    Log.d(TAG, "User bereits registriert");


                } else {

                    Log.d(TAG, "User nicht registriert, starte RegisterActivity");

                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                }
            }
        }new RegisteredTask().execute();
    }

    /**
     * Startet AppointmentsActivity
     * @param button , der mit android:onClick im xml Layout eingebunden ist
     */
    public void startAppointments (View button) {

        Log.d(TAG, "Termine Button gedrückt");

        startActivity(new Intent(MainActivity.this, AppointmentsActivity.class));

    }

    /**
     * Startet InfosActivity
     * @param button , der mit android:onClick im xml Layout eingebunden ist
     */
    public void startInfos (View button) {

        Log.d(TAG, "Infos Button gedrückt");

        startActivity(new Intent(MainActivity.this, InfosActivity.class));

    }
    /**
     * Falls die YouTube App installiert ist, wird das Video in dieser gestartet. Falls nicht, dann erscheint ein Auswahlmenue.
     * @param button , der mit android:onClick im xml Layout eingebunden ist
     */
    public void startVideo (View button) {

            try {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + "ExG9CydpZO4"));
                startActivity(intent);

                Log.d(TAG, "Youtube App gestartet");

            } catch (ActivityNotFoundException ex) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + "ExG9CydpZO4"));
                startActivity(intent);

                Log.d(TAG, "Keine Youtube App vorhanden");

            }
    }
}



