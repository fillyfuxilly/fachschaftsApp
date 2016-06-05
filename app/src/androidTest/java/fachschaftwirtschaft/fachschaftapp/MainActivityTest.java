package fachschaftwirtschaft.fachschaftapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;



/**
 * @Author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest  {



    @Rule
    public final ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);


    /**
     * Sharedpreferences setzen, damit RegisterActivity nicht startet
     */
    @Test
    public void test1Register() {
        Activity activity = rule.getActivity();
        SharedPreferences prefs = activity.getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nameKey", "Matthias");
        editor.putString("gruppeKey", "8");
        editor.commit();
    }

    /**
     * Auf Termine klicken und Activity wechseln
     */
    @Test
    public void test2ClickTermineButton() {

        onView(withId(R.id.imageButton)).perform(click());
        onView(withId(R.id.imageButton)).check(doesNotExist());

    }

    /**
     * Auf Infos klicken und Activity wechseln
     */
    @Test
    public void test3ClickInfosButton() {

        onView(withId(R.id.imageButton_infos)).perform(click());
        onView(withId(R.id.imageButton_infos)).check(doesNotExist());

    }

    /**
     * SharedPreferences zurücksetzen
     */
    @Test
    public void test4ClearSharedPrefs() {
        Activity activity = rule.getActivity();
        SharedPreferences prefs = activity.getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}







