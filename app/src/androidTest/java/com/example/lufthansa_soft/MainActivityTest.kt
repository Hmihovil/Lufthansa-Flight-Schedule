package com.example.lufthansa_soft

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.lufthansa_soft.EspressoTestingIdlingResource.idlingResource
import com.example.lufthansa_soft.ui.main_ui.MainActivity
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() { // let espresso know to synchronize with background tasks
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun checkTheTakeOffIsclick() {
        onView(withId(R.id.text_takeoff)).perform(click())
        onView(withId(R.id.bottom_sheet_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkTheLandingIsClicked() {
        onView(withId(R.id.text_landing)).perform(click())
        onView(withId(R.id.bottom_sheet_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun checkClickOnRecycler() {
        onView(withId(R.id.text_takeoff)).perform(click())
        onView(withId(R.id.bottom_sheet_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.airport_list)).check(matches(isDisplayed()))
//        onView(withId(R.id.airport_list)).perform(actionOnItemAtPosition<>(0, ViewActions.click() ))

    }
}