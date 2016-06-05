
package fachschaftwirtschaft.fachschaftapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author Matthias Heinen
 */
public class RegisterActivity extends Activity {

    EditText ed1;
    Button b1;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed1=(EditText)findViewById(R.id.editText);

        b1=(Button)findViewById(R.id.button_r);


        final Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                String n  = ed1.getText().toString();
                String g  = dropdown.getSelectedItem().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("nameKey", n);
                editor.putString("gruppeKey", g);
                editor.apply();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                Toast.makeText(RegisterActivity.this,"Erfolgreich registriert", Toast.LENGTH_LONG).show();

            }
        });


    }
}