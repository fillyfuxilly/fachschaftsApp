package fachschaftwirtschaft.fachschaftapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import android.content.Intent;
import android.test.InstrumentationTestCase;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;


/**
 * @Author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {


    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new ActivityTestRule<>(
            RegisterActivity.class);


    @Before
    public void before(){
        Intents.init();
    }
    @After
    public void after() {
        Intents.release();
    }
    @Test
    public void typeName() {
        onView(withId(R.id.editText)).perform(typeText("MatThias"), closeSoftKeyboard());
        onView(withId(R.id.editText)).check(matches(withText("MatThias")));
    }
    @Test
    public void selectGroup() {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("3"))));
    }
    @Test
    public void registerClick() {
        onView(withId(R.id.editText)).perform(typeText("MatThias"), closeSoftKeyboard());
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("3"))).perform(click());
        onView(withId(R.id.button_r)).perform(click());
        intended(hasComponent(MainActivity.class.getName()), times(1));
    }

}







