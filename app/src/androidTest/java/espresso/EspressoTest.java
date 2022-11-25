package espresso;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.ass2.i190626_190438.MainActivity;
import com.ass2.i190626_190438.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void musifyIsDisplayedTextView()
    {
        onView(withText("Musify")).check(matches(isDisplayed()));
    }
}
