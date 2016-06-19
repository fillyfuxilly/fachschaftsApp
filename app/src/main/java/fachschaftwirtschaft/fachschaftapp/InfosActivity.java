package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;


/**
 *  @author Wendy Frevert
 *
 *  Zeigt Infos, Links zu nützlichen Apps sowie den Treffpunkt für die Aaseerallye
 */
public class InfosActivity extends BaseActivity {

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        /** zurueck zur Startseite */
        backBtn = (Button) findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( InfosActivity.this, MainActivity.class));
            }
        });


    }

    /** Öffnet den Link zur Installation der "MünsterApp" direkt im Google Play Store */
    public void startMsApp(View button){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=de.swms.muenster"));
        startActivity(intent);
    }

    /** Öffnet den Link zur Installation der "MensaApp" im Browser */
    public void startMensaApp(View button){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=de.malte.mensa"));
        startActivity(intent);
    }
}


