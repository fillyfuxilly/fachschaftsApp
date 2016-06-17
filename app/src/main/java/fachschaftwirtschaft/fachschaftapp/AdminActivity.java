package fachschaftwirtschaft.fachschaftapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.GregorianCalendar;

import webService.Appointment;

/**
 * Activity zur Administration.
 * Aktuell kann ein neuer Termin erstellt werden. Sobald der Server es zulaesst, wird um eine Funktionalitaet zum Senden von Nachrichten ergaenzt.
 * @author Matthias Heinen
 */
public class AdminActivity extends BaseActivity {


    /**
     * Eingabefeld fuer den Titel des Termins.
     */
    EditText title;
    /**
     * Eingabefeld fuer den Ort des Termins.
     */
    EditText location;
    /**
     * Eingabefeld fuer die Beschreibung des Termins.
     */
    EditText description;
    /**
     * NumberPicker bei dem die Gruppennummer des Termins ausgewaehlt wird. 0 falls der Termin fuer alle Gruppen ist.
     */
    NumberPicker numberPicker;
    /**
     * TimePicker bei dem die Uhrzeit des Termins ausgewaehlt wird.
     */
    TimePicker timePicker;
    /**
     * DatePicker bei dem das Datum des Termins ausgewaehlt wird.
     */
    DatePicker datePicker;
    /**
     * Konstante zum loggen.
     */
    private static final String TAG = "AdminActivity";
    /**
     * Zusammen mit networkInfo zum Testen einer aktiven Internetverbindung..
     */
    ConnectivityManager connMgr;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Log.d(TAG, "Layout geladen");


        numberPicker = (NumberPicker) findViewById(R.id.admin_numberPicker);


        timePicker = (TimePicker) findViewById(R.id.admin_timePicker);


        try {
            timePicker.setIs24HourView(true);
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(10);
            numberPicker.setWrapSelectorWheel(false);
        } catch(NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "Problem mit den Pickern");
            Toast.makeText(AdminActivity.this, "Etwas ist schief gelaufen", Toast.LENGTH_LONG).show();
            recreate();
        }
    }

    /**
     * AsyncTask mit dem ein neuer Termin beim Web Service angelegt werden kann.
     */
    class AsyncAddAppointment extends AsyncTask<Appointment, Void, String> {

        @Override
        protected String doInBackground(Appointment... params) {

            Log.d(TAG, "AsnyTask zum Termin erstellen läuft");
            return ErstiHelferClient.createAppointment(params[0]);

        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(AdminActivity.this, result, Toast.LENGTH_LONG).show();
            recreate();
        }

    }

    /**
     * Sammelt Daten fuer einen neuen Termin und startet einen AsnycTask, um den Termin beim Web Service zu persistieren.
     * @param button der mit android:onClick im xml Layout eingebunden ist
     */
    public void newAppointment(View button) {


        /**
         * Prueft, ob Verbindung zum Netzwerk besteht. Falls nein wird der Task nicht ausgefuehrt.
         */
        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

                title = (EditText) findViewById(R.id.admin_editText);
                location = (EditText) findViewById(R.id.admin_editText2);
                description = (EditText) findViewById(R.id.admin_editText3);
                datePicker = (DatePicker) findViewById(R.id.admin_datePicker);

                if(title == null || location == null || description == null)Toast.makeText(AdminActivity.this, "Du musst alle Felder ausfüllen!", Toast.LENGTH_LONG).show();

                else {

                    GregorianCalendar gc = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());

                    Appointment appointment = new Appointment(title.getText().toString(), location.getText().toString(), gc, description.getText().toString(), numberPicker.getValue());

                    Log.d(TAG, "Starte AsyncAddAppointment");
                    new AsyncAddAppointment().execute(appointment);
                }


        } else {
            Log.e(TAG, "Keine Internetverbindung");
            Toast.makeText(AdminActivity.this, "Verbindung zum Internet benötigt", Toast.LENGTH_LONG).show();
        }
    }
}

