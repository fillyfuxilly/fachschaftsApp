package fachschaftwirtschaft.fachschaftapp;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.intent.Intents;
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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.*;


/** Instrumented Espresso Test für MainActivity.
 * @author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> rule = new ActivityTestRule<>(RegisterActivity.class);


    /**
     * Methode, um bei einem NumberPicker eine Zahl zu setzen.
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
     * Prüft ob die Texteingabe funktioniert.
     */
    @Test
    public void test1TypeName() throws Exception {

        onView(withId(R.id.register_editText)).perform(click());
        onView(withId(R.id.register_editText)).perform(typeText("Matthias"));
        onView(withId(R.id.register_editText)).check(matches(withText("Matthias")));
    }

    /**
     * Prüft, ob die Gruppenauswahl funktioniert.
     */
    @Test
    public void test2SelectGroup()throws Exception {
        onView(ViewMatchers.withId(R.id.numberPicker)).perform(selectCurrentNumber(2));
    }

    /**
     * Prüft, ob der Registrierungsprozess funktioniert.
     */
    @Test
    public void test3RegisterClick()throws Exception {

        try {
            Intents.init();
            onView(ViewMatchers.withId(R.id.register_editText)).perform(typeText("Matthias"), closeSoftKeyboard());
            onView(ViewMatchers.withId(R.id.numberPicker)).perform(selectCurrentNumber(3));
            onView(ViewMatchers.withId(R.id.button_r)).perform(click());
            intended(hasComponent(MainActivity.class.getName()), times(1));
        } catch(Exception e){e.printStackTrace();}
        finally {
            Intents.release();
        }
    }

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







