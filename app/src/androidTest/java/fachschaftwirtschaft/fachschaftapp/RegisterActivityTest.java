package fachschaftwirtschaft.fachschaftapp;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.NumberPicker;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;


/**
 * @author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> rule = new ActivityTestRule<>(RegisterActivity.class);


    /**
     * Prüft ob die Texteingabe funktioniert
     */
    @Test
    public void test1TypeName() throws Exception {

        onView(withId(R.id.register_editText)).perform(click());
        onView(withId(R.id.register_editText)).perform(typeText("Matthias"));
        onView(withId(R.id.register_editText)).check(matches(withText("Matthias")));
    }

    /**
     * Prüft, ob die Gruppenauswahl funktioniert
     */
    @Test
    public void test2SelectGroup()throws Exception {
        onView(ViewMatchers.withId(R.id.numberPicker)).perform(click());
        onView(ViewMatchers.withId(R.id.numberPicker)).perform(swipeDown());
    }

    /**
     * Prüft, ob der Registrierungsprozess funktioniert.
     */
    /*
    @Test
    public void test3RegisterClick()throws Exception {
        onView(ViewMatchers.withId(R.id.editText)).perform(typeText("MatThias"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());
        onView(ViewMatchers.withId(R.id.button_r)).perform(click());
        onView(withId(R.id.spinner)).check(doesNotExist());
    }*/

    /**
     * Setzt die Registrierung wieder zurück.
     */
    @Test
    public void test4ClearSharedPrefs()throws Exception {
        Activity activity = rule.getActivity();
        SharedPreferences prefs = activity.getSharedPreferences("Registrierung", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}







