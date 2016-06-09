package fachschaftwirtschaft.fachschaftapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import webService.Appointment;

/**
 * @author Matthias Heinen
 */
public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



    }

    class AsyncCreateAppointment extends AsyncTask<Appointment, Void, Void> {
        @Override
        protected Void doInBackground(Appointment... params) {
            //Invoke webservice
            ErstiHelferClient.createAppointment(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            Toast.makeText(AdminActivity.this,"Termin erstellt", Toast.LENGTH_LONG).show();

        }

    }
    public void newAppointment(View button) {
        EditText title = (EditText) findViewById(R.id.admin_et1);
        EditText location = (EditText) findViewById(R.id.admin_et2);
        EditText beschreibung = (EditText) findViewById(R.id.admin_et4);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Date date = (Date) new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

        Appointment appointment = new Appointment(title.toString(),location.toString(),date,beschreibung.toString());

        new AsyncCreateAppointment().execute(appointment);


    }
}
