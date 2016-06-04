package fachschaftwirtschaft.fachschaftapp;


import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;


/**
 * @Author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {
RegisterActivity activity;

    @Rule
    public ActivityTestRule<RegisterActivity> rule = new ActivityTestRule<>(RegisterActivity.class, true, false);

    @Before
    public void setUp() {
        Intents.init();
        activity = rule.launchActivity(null);
    }
    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void registerClick() {
        onView(ViewMatchers.withId(R.id.editText)).perform(typeText("MatThias"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());
        onView(ViewMatchers.withId(R.id.button_r)).perform(click());
        intending(toPackage(MainActivity.class.getName()));
    }

    @Test
    public void typeName() {
        onView(ViewMatchers.withId(R.id.editText)).perform(typeText("MatThias"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.editText)).check(matches(withText("MatThias")));
    }
    @Test
    public void selectGroup() {
        onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());
        onView(ViewMatchers.withId(R.id.spinner)).check(matches(withSpinnerText(containsString("3"))));
    }

}







