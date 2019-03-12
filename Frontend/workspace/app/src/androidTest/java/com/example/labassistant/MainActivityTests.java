package com.example.labassistant;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.labassistant.app.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMessageViewVisibility(){
        onView(withId(R.id.messages_view)).check(matches((isDisplayed())));
    }

    @Test
    public void testEditTestForMessage() {
        String newMessage = "This is a new message";
        onView(withId(R.id.chatEditText)).perform(typeText(newMessage)).check(
                matches(withText(newMessage)));
    }

    @Test
    public void testTopMessageToTop(){
        String firstMessage = "hey can someone help me I have problem plz help";
        onData(anything()).inAdapterView(withId(R.id.messages_view)).onChildView(
                        withId(R.id.messageContentTextView)).atPosition(0).check(
                                matches(withText(firstMessage)));
    }

    @Test
    public void testGoToQueueViewThenBackToChatView(){
        onView(withId(R.id.navigationQueue)).perform(click());
        onView(withId(R.id.navigationChat)).perform(click());

        testMessageViewVisibility();
        testEditTestForMessage();
        testTopMessageToTop();
    }

    @Test
    public void testGoToClassworkViewThenBackToChatView(){
        onView(withId(R.id.navigationClasswork)).perform(click());
        onView(withId(R.id.navigationChat)).perform(click());

        testMessageViewVisibility();
        testEditTestForMessage();
        testTopMessageToTop();
    }

    @Test
    public void testGoToSettingViewThenBackToChatView(){
        onView(withId(R.id.navigationSettings)).perform(click());
        onView(withId(R.id.navigationChat)).perform(click());

        testMessageViewVisibility();
        testEditTestForMessage();
        testTopMessageToTop();
    }

    @Test
    public void testRepeatGoingToChatViewAndTestBasicFunctionalities(){
        onView(withId(R.id.navigationChat)).perform(click());
        onView(withId(R.id.navigationChat)).perform(click());
        onView(withId(R.id.navigationChat)).perform(click());

        testMessageViewVisibility();
        testEditTestForMessage();
        testTopMessageToTop();
    }
}
