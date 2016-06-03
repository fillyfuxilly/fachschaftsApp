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
import static android.support.test.espresso.matcher.ViewMatchers.withText;




/**
 * @Author Matthias Heinen
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void before() {
        Intents.init();
    }

    @After
    public void after() {
        Intents.release();
    }
    @Test
    public void clickTermineButton() {
        onView(withId(R.id.imageButton)).perform(click());
        intended(hasComponent(TermineActivity.class.getName()), times(1));
        intended(hasComponent(InfosActivity.class.getName()), times(0));
    }
    @Test
    public void clickInfosButton() {
        onView(withId(R.id.imageButton_infos)).perform(click());
        intended(hasComponent(TermineActivity.class.getName()), times(0));
        intended(hasComponent(InfosActivity.class.getName()), times(1));
    }
}







