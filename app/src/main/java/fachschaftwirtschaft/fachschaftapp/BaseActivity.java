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
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.admin);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new AsyncIsAdmin().execute(getSharedPreferences("Registrierung", Context.MODE_PRIVATE).getString("nameKey", ""));

            if (!checkIsAdmin) {

                item.setVisible(false);
            }
        }
        return true;
    }

    /**
     * Definiert die Items des Menues.
     * @param item
     * @return
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
