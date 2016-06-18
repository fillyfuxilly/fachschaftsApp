package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import webService.Appointment;

/**
 * @author Wendy Frevert
 */

public class AppointmentsActivity extends BaseActivity {

    Button backBtn;
    ListView listView;
    String[] list = new String[5];
    private static final String TAG = "AppointmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        Log.d(TAG, "Layout geladen");

            /** AsyncTask zum Abruf der Termine */
            class ScheduledTask extends AsyncTask<Void,Void,Boolean> {

                @Override
                protected Boolean doInBackground(Void... params) {
                    try {
                        SharedPreferences sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                        int group = Integer.parseInt(sharedpreferences.getString("gruppeKey", ""));

                        Appointment[] a = ErstiHelferClient.getAppointments(5, group);

                        for (int i = 0; i < a.length; i++) {
                            /** Gregorian Calendar Minutes ist ein Integer, deshalb muss für die Darstellung der Zeit eine Null hinzugefügt werden, wenn der Wert < 10 ist.*/
                            String gcm;

                            if (a[i].getDate().getTime().getMinutes() < 10) {
                                gcm = ":0";
                            }
                            else{
                                gcm = ":";
                            }
                            list[i] = a[i].getDate().getTime().getHours() + gcm + a[i].getDate().getTime().getMinutes() + "      |       " + a[i].getLocation() + "         |        " + a[i].getTitle() + "\n\n" + a[i].getDescription() + "\n";
                        }

                        Log.d(TAG, "Termine wurden geladen");
                        return true;
                    } catch (Exception e) {
                        Log.d(TAG, "Termine konnten nicht geladen werden");
                        Toast.makeText(AppointmentsActivity.this, "Termine können nicht geladen werden", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    if (result) {
                        Log.d(TAG, "Anzeigen der Termine");
                        //Zugriff auf die ListView
                        listView = (ListView) findViewById(R.id.listView);

                        ArrayAdapter<String> adapter =

                                new ArrayAdapter<>(AppointmentsActivity.this, android.R.layout.simple_list_item_1, list);

                        listView.setAdapter(adapter);
                    }
                }
            }
                            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
                    new ScheduledTask().execute();
                } else {
                    Log.d(TAG, "Keine Internetverbindung");

                }


                        // Zurueck zur Startseite
                        backBtn = (Button) findViewById(R.id.back);
                        backBtn.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(AppointmentsActivity.this, MainActivity.class));
                            }
                        });
                    }


                }



