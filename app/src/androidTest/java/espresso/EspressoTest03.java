package espresso;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.ass2.i190626_190438.MainActivity;
import com.ass2.i190626_190438.SelectingAccount;
import com.ass2.i190626_190438.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest03 {

    @Rule
    public ActivityScenarioRule<SelectingAccount> activityScenarioRule =
            new ActivityScenarioRule<SelectingAccount>(SelectingAccount.class);

    @Test
    public void createAccountisClicked()
    {
        onView(withId(R.id.createaccount)).perform(ViewActions.click());
    }
}
