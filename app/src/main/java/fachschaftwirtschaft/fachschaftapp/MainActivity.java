package fachschaftwirtschaft.fachschaftapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * @author Matthias Heinen
 */
public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageButton ibtn;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Layout geladen");


        /**
         * AsnyTask der prüft, ob der User sich bereits registriert hat. Falls ja wird MainActivity mit Funktionalität versehen.
         * Falls nein, dann wird der RegisterActivity Dialog gestartet.
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

                    btn = (Button) findViewById(R.id.main_button_clear);

                    btn.setOnClickListener(new View.OnClickListener() {


                        @Override
                        public void onClick(View v) {
                            SharedPreferences sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                            SharedPreferences.Editor e = sharedpreferences.edit();
                            e.clear();
                            e.apply();

                            Toast.makeText(MainActivity.this,"Daten gelöscht", Toast.LENGTH_LONG).show();

                        }
                    });

                    ibtn = (ImageButton) findViewById(R.id.main_imageButton2);

                    ibtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this, InfosActivity.class));

                        }
                    });
                } else {

                    Log.d(TAG, "User nicht registriert, starte RegisterActivity");

                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                }
            }
        }new RegisteredTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            return true;
        }
        if (id == R.id.admin) {
            startActivity(new Intent(MainActivity.this, AdminActivity.class));

            return true;
        }
        if (id == R.id.register) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
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
     * Falls Youtube App installiert, starte Video in dieser, sonst Auswahlmenü
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



