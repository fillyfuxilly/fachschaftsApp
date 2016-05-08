/**
 * @Author Matthias Heinen
 */
package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageButton ibtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        class RegisteredTask extends AsyncTask<Void,Void,Boolean> {

            @Override
            protected Boolean doInBackground(Void... params){
                SharedPreferences sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                String name = sharedpreferences.getString("nameKey", "");
                Boolean result = false;
                if(name.equals(""))return result;

                else {
                    result = true;
                    return result;
                }

            }
            @Override
            protected void onPostExecute(Boolean result) {
                if(result) {
                    setContentView(R.layout.activity_main);

                    btn = (Button) findViewById(R.id.button_termine);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this, TermineActivity.class));
                        }
                    });

                    btn = (Button) findViewById(R.id.button_gr);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                        }
                    });

                    btn = (Button) findViewById(R.id.button_clear);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences sharedpreferences = getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
                            SharedPreferences.Editor e = sharedpreferences.edit();
                            e.clear();
                            e.apply();
                            Toast.makeText(MainActivity.this,"Daten gelöscht", Toast.LENGTH_LONG).show();
                        }
                    });

                    ibtn = (ImageButton) findViewById(R.id.imageButton_infos);

                    ibtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this, InfosActivity.class));
                        }
                    });
                } else startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }


        }new RegisteredTask().execute();

    }



}



