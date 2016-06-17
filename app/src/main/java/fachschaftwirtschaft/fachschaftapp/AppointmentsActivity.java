package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import webService.Appointment;

public class AppointmentsActivity extends AppCompatActivity {

    Button backBtn;
    ListView listView;
    String[] list={"12:00    |    Raum D227   |   Irgendeine Aktivität","13:30    |   Torminbrücke    | Aaseerallye","19:30   |   Unterwasserkirche   |   Arielle","8:30  |   Raum A004   |   Katerfrühstück","9:00   |... hatte keinen Bock mir nen 5. Termin auszudenken", "Weil es ja eh mit den Seeds gemacht werden muss"};
    //String[] allInOne = {};
    private static final String TAG = "AppointmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        Log.d(TAG, "Layout geladen");

            /* //AsyncTask zum Abruf der Termine
            class ScheduledTask extends AsyncTask<Void,Void,Boolean> {

                @Override
                protected Boolean doInBackground(Void... params) {
                   try {
                       Appointment[] a = ErstiHelferClient.getAppointments(5);

                       for (int i = 0; i < a.length; i++) {
                           allInOne[i] = a[i].toString();
                       }
                       Log.d(TAG, "Termine wurden geladen");
                       return true;
                   }
                   catch (Exception e){
                       Log.d(TAG, "Termine konnten nicht geladen werden");
                       Toast.makeText(AppointmentsActivity.this,"Termine können nicht geladen werden", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    if(result){
                        Log.d(TAG, "Anzeigen der Termine"); */
        //Zugriff auf die ListView
        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter =

                new ArrayAdapter<String>(AppointmentsActivity.this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
                    /*}
                }
            }
            new ScheduledTask().execute(); */

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
