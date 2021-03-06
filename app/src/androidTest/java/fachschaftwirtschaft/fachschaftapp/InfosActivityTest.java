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

/**Testet den Button von Infos
 * @author Wendy Frevert
 */

@RunWith(AndroidJUnit4.class)
public class InfosActivityTest {

    @Rule
    public ActivityTestRule<InfosActivity> mActivityRule =
            new ActivityTestRule<>(InfosActivity.class);

    /**
     * Auf 'Zurueck zur Startseite' klicken und Activity wechseln
     */
    @Test
    public void testClickBackButton() throws Exception {

        onView(withId(R.id.back)).perform(scrollTo(), click());
        onView(withId(R.id.back)).check(doesNotExist());

    }
}
