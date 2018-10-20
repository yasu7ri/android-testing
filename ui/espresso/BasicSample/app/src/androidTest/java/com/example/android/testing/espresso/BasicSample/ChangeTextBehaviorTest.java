/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.assertEquals;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

//import androidx.test.core.app.ActivityScenario;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class ChangeTextBehaviorTest {
    public static final String STRING_TO_BE_TYPED = "Espresso";

    @Rule
    public ReportHelper reportHelper = Factory.getReportHelper();
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

//    @Before
//    public void launchActivity() {
//        ActivityScenario.launch(MainActivity.class);
//    }

    @Test
    public void changeText_sameActivity() {
        reportHelper.label("changeText_sameActivity-01");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.android.testing.espresso.BasicSample", appContext.getPackageName());
        reportHelper.label("changeText_sameActivity-02");
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard());
        reportHelper.label("changeText_sameActivity-03");
        onView(withId(R.id.changeTextBt)).perform(click());
        reportHelper.label("changeText_sameActivity-04");

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
        reportHelper.label("changeText_sameActivity-05");
    }

    @Test
    public void changeText_newActivity() {
        reportHelper.label("changeText_newActivity-01");
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard());
        reportHelper.label("changeText_newActivity-02");
        onView(withId(R.id.activityChangeTextBtn)).perform(click());
        reportHelper.label("changeText_newActivity-03");

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
        reportHelper.label("changeText_newActivity-04");
    }

    @After
    public void TearDown() {
        reportHelper.label("Stopping App");
    }

}