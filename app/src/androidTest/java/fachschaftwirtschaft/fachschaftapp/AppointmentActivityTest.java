package fachschaftwirtschaft.fachschaftapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * @Author Wendy Frevert
 */

@RunWith(AndroidJUnit4.class)
public class AppointmentActivityTest {

    @Rule
    public ActivityTestRule<AppointmentsActivity> mActivityRule =
            new ActivityTestRule<>(AppointmentsActivity.class);

    /**
     * Auf 'Zurueck zur Startseite' klicken und Activity wechseln
     */
    @Test
    public void testClickBackButton() throws Exception {

        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.back)).check(doesNotExist());

    }

}
