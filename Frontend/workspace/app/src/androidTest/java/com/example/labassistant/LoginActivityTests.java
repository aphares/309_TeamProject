package com.example.labassistant;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.labassistant.app.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.CoreMatchers.anything;
import android.support.test.espresso.IdlingResource;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTests {

    ViewInteraction usernameViewInteraction;
    ViewInteraction passwordViewInteraction;
    ViewInteraction signInViewInteraction;
    ViewInteraction loginImageViewInteraction;

    String dummyUsername, dummyPassword;
    String incorrectUsernmae, incorrectPassword;


    @Rule
    public ActivityTestRule<Login> activityActivityTestRule =
            new ActivityTestRule<>(Login.class);

    @Before
    public void initSetup(){
        usernameViewInteraction = onView(withId(R.id.usernameEditText));
        passwordViewInteraction = onView(withId(R.id.passwordEditText));
        signInViewInteraction = onView(withId(R.id.signInBtn));
        loginImageViewInteraction = onView(withId(R.id.loginImage));

        dummyUsername = "isu_grad324";
        dummyPassword = "iamcool33";

        incorrectUsernmae = "alice";
        incorrectPassword = "alicePassword";
    }

    @Test
    public void testInitLayout(){
        usernameViewInteraction.check(matches(isDisplayed()));
        passwordViewInteraction.check(matches(isDisplayed()));
        signInViewInteraction.check(matches(isDisplayed()));
        loginImageViewInteraction.check(matches(isDisplayed()));
    }

    @Test
    public void testSimpleTypingOnUsername(){
        usernameViewInteraction.perform(typeText(dummyUsername)).check(
                matches(withText(dummyUsername)));
    }

    @Test
    public void testSimpleTypingOnPassword(){
        passwordViewInteraction.perform(typeText(dummyPassword), closeSoftKeyboard()).check(
                matches(withText(dummyPassword)));
    }

    @Test
    public void testSimpleSignInTouch(){
        signInViewInteraction.perform(click());
        testInitLayout();
    }

    @Test
    public void testAttemptToLoginWithOnlyUsername(){
        usernameViewInteraction.perform(typeText(dummyUsername), closeSoftKeyboard());
        signInViewInteraction.perform(click());
        testInitLayout();
    }

    @Test
    public void testAttemptToLoginWithOnlyPassword(){
        passwordViewInteraction.perform(typeText(dummyPassword), closeSoftKeyboard());
        signInViewInteraction.perform(click());
        testInitLayout();
    }

    @Test
    public void testSuccessfulLogin(){
        usernameViewInteraction.perform(typeText(dummyUsername), closeSoftKeyboard());
        passwordViewInteraction.perform(typeText(dummyPassword), closeSoftKeyboard());
        signInViewInteraction.perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

//    @Test
//    public void testAllLowerCaseUsernameSuccessfulLogin(){
//        dummyUsername = dummyUsername.toLowerCase();
//        usernameViewInteraction.perform(typeText(dummyUsername));
//        passwordViewInteraction.perform(typeText(dummyPassword));
//        signInViewInteraction.perform(click());
//        String mainActivity = MainActivity.class.getName();
//        Espresso.registerIdlingResources(
//                new WaitActivityIsResumedIdlingResource(mainActivity));
//        intended(hasComponent(mainActivity));
//    }
}
