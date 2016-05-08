package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1;
    SharedPreferences sharedpreferences;
    ImageButton ibtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);

        b1=(Button)findViewById(R.id.button_r);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                String n  = ed1.getText().toString();
                String g  = ed2.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("nameKey", n);
                editor.putString("gruppeKey", g);
                editor.apply();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                Toast.makeText(RegisterActivity.this,"Erfolgreich registriert", Toast.LENGTH_LONG).show();

            }
        });

        /*ibtn = (ImageButton) findViewById(R.id.imageButton_back);

        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });*/
    }
}