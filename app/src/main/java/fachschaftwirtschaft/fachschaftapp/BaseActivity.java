package fachschaftwirtschaft.fachschaftapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Erstellt ein Menue in der Activity.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
        if (id == R.id.settings) {
            startActivity(new Intent(BaseActivity.this, SettingsActivity.class));

            return true;
        }
        if (id == R.id.admin) {
            startActivity(new Intent(BaseActivity.this, AdminActivity.class));

            return true;
        }
        if (id == R.id.register) {
            startActivity(new Intent(BaseActivity.this, RegisterActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
