package fachschaftwirtschaft.fachschaftapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;
import java.util.GregorianCalendar;

import webService.Appointment;

/**
 * @author Matthias Heinen
 */
public class AdminActivity extends AppCompatActivity {

    NumberPicker picker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        picker = (NumberPicker) findViewById(R.id.admin_numberPicker);
        timePicker = (TimePicker) findViewById(R.id.admin_timePicker);


        try {
            timePicker.setIs24HourView(true);
            picker.setMinValue(0);
            picker.setMaxValue(10);
            picker.setWrapSelectorWheel(false);
        } catch(NullPointerException e) {
            e.printStackTrace();
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

            return ErstiHelferClient.createAppointment(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(AdminActivity.this, result, Toast.LENGTH_LONG).show();
            recreate();
        }

    }

    /**
     * Sammelt Daten für einen neuen Termin und startet einen AsnycTask, um den Termin beim Web Service zu persistieren.
     * @param button der mit android:onClick im xml Layout eingebunden ist
     */
    public void newAppointment(View button) {

        EditText title = (EditText) findViewById(R.id.admin_editText);
        EditText location = (EditText) findViewById(R.id.admin_editText2);
        EditText description = (EditText) findViewById(R.id.admin_editText3);
        DatePicker datePicker = (DatePicker) findViewById(R.id.admin_datePicker);

        try {
            GregorianCalendar gc = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());

            Appointment appointment = new Appointment(title.getText().toString(), location.getText().toString(), gc, description.getText().toString(), picker.getValue());

            new AsyncAddAppointment().execute(appointment);
        } catch(NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(AdminActivity.this, "Etwas ist schief gelaufen", Toast.LENGTH_LONG).show();
            recreate();
        }


    }
}
