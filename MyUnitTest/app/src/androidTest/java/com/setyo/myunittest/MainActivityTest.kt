package com.setyo.myunittest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dummyVolume = "504.0"
    private val dummyCircumference = "100.0"
    private val dummySurfaceArea = "396.0"
    private val dummyLength = "12.0"
    private val dummyWidth = "7.0"
    private val dummyHeight = "6.0"
    private val emptyInput = ""
    private val fieldEmpty = "Field ini tidak boleh kosong"

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetCircumference() {
        onView(withId(R.id.edtLength)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edtWidth)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edtHeight)).perform(typeText(dummyHeight), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.btnCalculateCircumrefence)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCalculateCircumrefence)).perform(click())

        onView(withId(R.id.tvResult)).check(matches(isDisplayed()))
        onView(withId(R.id.tvResult)).check(matches(withText(dummyCircumference)))
    }

    @Test
    fun assertGetSurfaceArea() {
        onView(withId(R.id.edtLength)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edtWidth)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edtHeight)).perform(typeText(dummyHeight), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.btnCalculateSurfaceArea)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCalculateSurfaceArea)).perform(click())

        onView(withId(R.id.tvResult)).check(matches(isDisplayed()))
        onView(withId(R.id.tvResult)).check(matches(withText(dummySurfaceArea)))
    }

    @Test
    fun assertGetVolume() {
        onView(withId(R.id.edtLength)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edtWidth)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edtHeight)).perform(typeText(dummyHeight), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.btnCalculateVolume)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCalculateVolume)).perform(click())

        onView(withId(R.id.tvResult)).check(matches(isDisplayed()))
        onView(withId(R.id.tvResult)).check(matches(withText(dummyVolume)))
    }

    //Pengecekan untuk empty input
    @Test
    fun assertEmptyInput() {
        // pengecekan input untuk length
        onView(withId(R.id.edtLength)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.edtLength)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edtLength)).perform(typeText(dummyLength), closeSoftKeyboard())

        // pengecekan input untuk Width
        onView(withId(R.id.edtWidth)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.edtWidth)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edtWidth)).perform(typeText(dummyWidth), closeSoftKeyboard())

        // pengecekan input untuk height
        onView(withId(R.id.edtHeight)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSave)).perform(click())

        onView(withId(R.id.edtHeight)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edtHeight)).perform(typeText(dummyHeight), closeSoftKeyboard())
    }


}