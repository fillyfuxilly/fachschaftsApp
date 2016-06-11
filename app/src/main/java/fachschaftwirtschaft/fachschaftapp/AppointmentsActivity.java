package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class AppointmentsActivity extends AppCompatActivity {

        Button zb;
        Boolean result;
        ListView listView;
        String[] listPlanet={"12:00    |    Raum D227   |   Irgendeine Aktivität","13:30    |   Torminbrücke    | Aaseerallye","19:30   |   Unterwasserkirche   |   Arielle","8:30  |   Raum A004   |   Katerfrühstück","9:00   |... hatte keinen Bock mir nen 5. Termin auszudenken", "Weil es ja eh mit den Seeds gemacht werden muss"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_termine);
//Zugriff ListView


            listView = (ListView)findViewById(R.id.listView);

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listPlanet);

            listView.setAdapter(adapter);
//

        // zurueck zur Startseite
        zb = (Button) findViewById(R.id.zurueck);
        zb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentsActivity.this, MainActivity.class));
            }
        });
    }


}
