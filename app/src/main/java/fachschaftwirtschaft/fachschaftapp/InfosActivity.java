package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// @author Wendy Frevert
public class InfosActivity extends AppCompatActivity {

    //TextView tv; brauch ich vllt später nochma
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        // zurueck zur Startseite
        backBtn = (Button) findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( InfosActivity.this, MainActivity.class));
            }
        });

        //tv = (TextView) findViewById(R.id.info1); brauch ich später vllt nochma

    }

    public void startMsApp(View button){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=de.swms.muenster"));
        startActivity(intent);
    }

    public void startMensaApp(View button){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=de.malte.mensa"));
        //intent.setData(Uri.parse("market://details?id=de.malte.mensa"));
        startActivity(intent);
    }
}


