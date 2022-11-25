package espresso;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.ass2.i190626_190438.SelectingAccount;
import com.ass2.i190626_190438.SignUp;
import com.ass2.i190626_190438.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class EspressoTest02 {

    @Rule
    public ActivityScenarioRule<SignUp> activityScenarioRule =
            new ActivityScenarioRule<SignUp>(SignUp.class);

    @Test
    public void signupCheckBoxisClicked()
    {
        onView(withId(R.id.checkbox)).perform(ViewActions.click());
    }
}
