
package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * @author Matthias Heinen
 */
public class EinstellungenActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);


        sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);

        EditText name = (EditText) findViewById(R.id.editText);
        name.setText(sharedpreferences.getString("nameKey", ""));

        EditText gruppe = (EditText) findViewById(R.id.editText2);
        gruppe.setText(sharedpreferences.getString("gruppeKey", ""));



    }
}
