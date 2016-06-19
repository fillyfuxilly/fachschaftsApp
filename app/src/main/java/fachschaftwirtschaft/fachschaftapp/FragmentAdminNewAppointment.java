package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.GregorianCalendar;

import webService.Appointment;

/**
 * Fragment fuer den Admin, um einen neuen Termin zu erstellen.
 *
 * @author Matthias Heinen
 */
public class FragmentAdminNewAppointment extends Fragment {

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
    private static final String TAG = "AddAppointmentFragment";
    /**
     * Zusammen mit networkInfo zum Testen einer aktiven Internetverbindung..
     */
    ConnectivityManager connMgr;
    NetworkInfo networkInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_new_appointment, container, false);

        Log.d(TAG, "Layout geladen");

        numberPicker = (NumberPicker) rootView.findViewById(R.id.admin_numberPicker);


        timePicker = (TimePicker) rootView.findViewById(R.id.admin_timePicker);

        title = (EditText) rootView.findViewById(R.id.admin_editText);
        location = (EditText) rootView.findViewById(R.id.admin_editText2);
        description = (EditText) rootView.findViewById(R.id.admin_editText3);
        datePicker = (DatePicker) rootView.findViewById(R.id.admin_datePicker);

        try {
            timePicker.setIs24HourView(true);
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(10);
            numberPicker.setWrapSelectorWheel(false);

        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Etwas ist schief gelaufen", Toast.LENGTH_LONG).show();
        }

        Button btn = (Button) rootView.findViewById(R.id.admin_button);

        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

        /**
         * Prueft, ob Verbindung zum Netzwerk besteht. Falls nein wird der Task nicht ausgefuehrt.
         */

        connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {


            if (title.getText().toString().equals("") || location.getText().toString().equals("") || description.getText().toString().equals("")) {

                Toast.makeText(getActivity(), "Du musst alle Felder ausfüllen!", Toast.LENGTH_LONG).show();
            } else {
                GregorianCalendar gc = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());

                Appointment appointment = new Appointment(title.getText().toString(), location.getText().toString(), gc, description.getText().toString(), numberPicker.getValue());

                Log.d(TAG, "Starte AsyncAddAppointment");
                new AsyncAddAppointment().execute(appointment);
            }


        } else {
            Log.e(TAG, "Keine Internetverbindung");
            Toast.makeText(getActivity(), "Verbindung zum Internet benötigt", Toast.LENGTH_LONG).show();
        }


            }
        });

        return rootView;
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

            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        }

    }
}
