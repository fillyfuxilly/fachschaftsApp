package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// @author Wendy Frevert
public class InfosActivity extends AppCompatActivity {

    //TextView tv; brauch ich vllt später nochma
    Button zurueck_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);

        // zurueck zur Startseite
        zurueck_btn = (Button) findViewById(R.id.zurueck);
        zurueck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( InfosActivity.this, MainActivity.class));
            }
        });

        //tv = (TextView) findViewById(R.id.info1); brauch ich später vllt nochma

    }
}


