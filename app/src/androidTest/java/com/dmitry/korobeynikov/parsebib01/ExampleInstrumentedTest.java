package com.dmitry.korobeynikov.parsebib01;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test(expected = OutOfMemoryError.class)
    public void scrollTest() {
        int n = 6000;
        for (int i = 0; i < n; i++) {
            int index = activityTestRule.getActivity().bibs.size() - 1;
            Espresso.onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition(index));
        }
    }
}
