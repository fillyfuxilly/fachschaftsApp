package fachschaftwirtschaft.fachschaftapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * @author Matthias Heinen
 */
public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageButton ibtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
/*                    WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
                    String frameVideo = "<iframe width=\"370\" height=\"290\" src=\"https://www.youtube.com/embed/ExG9CydpZO4\" frameborder=\"0\" allowfullscreen></iframe>";

                    displayYoutubeVideo.setVisibility(View.VISIBLE);
                    displayYoutubeVideo.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            return false;
                        }
                    });
                    WebSettings webSettings = displayYoutubeVideo.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
*/
                } else startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        }new RegisteredTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.einstellungen) {
            startActivity(new Intent(MainActivity.this, EinstellungenActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startTermine (View button) {
        startActivity(new Intent(MainActivity.this, TermineActivity.class));
    }

    /**
     * Falls Youtube App installiert, starte Video in dieser, sonst Auswahlmenü
     * @param button, der mit android:onClick in xml eingebunden ist
     */
    public void startVideo (View button) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + "ExG9CydpZO4"));
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + "ExG9CydpZO4"));
                startActivity(intent);
            }
    }
}



