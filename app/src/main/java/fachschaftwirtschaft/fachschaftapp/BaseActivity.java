package fachschaftwirtschaft.fachschaftapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


/** Zentrale Activity die Funktionalitaet fuer alle Activities, die von ihr erben, bereit stellt.
 * @author Matthias Heinen
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Wird mit dem Ergebnis von ErstiHelfer.isAdmin() befuellt.
     */
    boolean checkIsAdmin = false;

    /**
     * Erstellt ein Menue.
     * @param menu Ein Menue für Activities
     * @return Nach erfolgreicher Erzeugung wird true ausgegeben.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        return true;
    }

    /**
     * Definiert die Items des Menues.
     * @param item Die einzelnen Menueeintraege
     * @return Nach starten der Activity wird true ausgegeben.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.register) {
            startActivity(new Intent(BaseActivity.this, RegisterActivity.class));

            return true;
        }
        if (id == R.id.settings) {
            startActivity(new Intent(BaseActivity.this, SettingsActivity.class));

            return true;
        }
        if (id == R.id.admin) {
            startActivity(new Intent(BaseActivity.this, AdminActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem item = menu.findItem(R.id.admin);
        MenuItem item2 = menu.findItem(R.id.register);
        item.setVisible(false);
        item2.setVisible(false);

        String userName = getSharedPreferences("Registrierung", Context.MODE_PRIVATE).getString("nameKey", "");

        if(userName.equals("")) item2.setVisible(true);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            new AsyncIsAdmin().execute(userName);

            if (checkIsAdmin) item.setVisible(true);

        }else Toast.makeText(this, "Verbindung zum Internet benötigt", Toast.LENGTH_LONG).show();


        return true;
    }

    /**
     * AsnycTask zur Pruefung, ob ein User Admin Privilegien besitzt.
     */
    class AsyncIsAdmin extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            checkIsAdmin = ErstiHelferClient.isAdmin(params[0]);
            return null;
        }


    }
}
