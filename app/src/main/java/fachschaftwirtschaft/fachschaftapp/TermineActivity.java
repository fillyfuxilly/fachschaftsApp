package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TermineActivity extends AppCompatActivity {

    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termine);

        // zurueck zur Startseite
        tv2 = (TextView) findViewById(R.id.zurueck);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TermineActivity.this, MainActivity.class));
            }
        });
    }

}
