package fachschaftwirtschaft.fachschaftapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.NumberPicker;

import org.hamcrest.Matcher;
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
 * Instrumented Espresso Test für SettingsActivity
 *
 * @author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> rule = new ActivityTestRule<>(SettingsActivity.class);

    /**
     * Methode, um bei einem NumberPicker eine Zahl zu setzen.
     *
     * @param number Zahl die gesetzt werden soll.
     * @return ViewAction
     */
    public static ViewAction selectCurrentNumber(final int number) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                NumberPicker numberPicker = (NumberPicker) view;
                numberPicker.setValue(number);
            }

            @Override
            public String getDescription() {
                return "Set the passed value into the NumberPicker";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }
        };
    }

    /**
     * Sharedpreferences setzen, damit RegisterActivity nicht startet.
     */
    @Test
    public void test1Register() {
        Activity activity = rule.getActivity();
        SharedPreferences prefs = activity.getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nameKey", "Matthias");
        editor.putString("groupKey", "8");
        editor.apply();
    }

    /**
     * Prueft, ob die Informationen aus den sharedPreferences korrekt gelesen werden.
     */
    @Test
    public void test2CheckValues() {
        onView(withId(R.id.settings_textView2)).check(matches(withText("Matthias")));
        onView(withId(R.id.settings_textView4)).check(matches(withText("8")));
    }

    /**
     * Prueft, ob User erfolgreich ihre Gruppe wechseln koennen.
     */
    @Test
    public void test3CheckGroupChange() {

        onView(withId(R.id.settings_numberPicker)).perform(selectCurrentNumber(2));
        onView(withId(R.id.settings_btn)).perform(click());

    }

    /**
     * SharedPreferences zurücksetzen.
     */
    @Test
    public void test4ClearSharedPrefs() {
        Activity activity = rule.getActivity();
        SharedPreferences prefs = activity.getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
