package com.example.earthquakes.tests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.earthquakes.R
import com.example.earthquakes.presentation.preferences.PrefsActivity
import com.example.earthquakes.setup.base.InstrumentedTestSetup
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrefsActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<PrefsActivity> =
        ActivityTestRule(
            PrefsActivity::class.java,
            false, false
        )

    @Test
    fun testClickOnNorthBoundPreference() {
        // when
        testRule.launchActivity(null)

        // then
        onView(withId(androidx.preference.R.id.recycler_view))
            .perform(actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(R.string.pref_north_bound_title)), click()))
    }

    @Test
    fun testClickOnSouthBoundPreference() {
        // when
        testRule.launchActivity(null)

        // then
        onView(withId(androidx.preference.R.id.recycler_view))
            .perform(actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(R.string.pref_south_bound_title)), click()))
    }

    @Test
    fun testClickOnEastBoundPreference() {
        // when
        testRule.launchActivity(null)

        // then
        onView(withId(androidx.preference.R.id.recycler_view))
            .perform(actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(R.string.pref_east_bound_title)), click()))
    }

    @Test
    fun testClickOnWestBoundPreference() {
        // when
        testRule.launchActivity(null)

        // then
        onView(withId(androidx.preference.R.id.recycler_view))
            .perform(actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(R.string.pref_west_bound_title)), click()))
    }

    @Test
    fun testClickOnMaxQuakeResultsPreference() {
        // when
        testRule.launchActivity(null)

        // then
        onView(withId(androidx.preference.R.id.recycler_view))
            .perform(actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(R.string.pref_max_quakes_title)), click()))
    }

    @Test
    fun checkPreferenceViewsBySummaries() {
        // when
        testRule.launchActivity(null)

        // then
        onView(withId(androidx.preference.R.id.recycler_view)).check(
            matches(hasDescendant(withText(R.string.pref_north_bound_summary)))
        )

        onView(withId(androidx.preference.R.id.recycler_view)).check(
            matches(hasDescendant(withText(R.string.pref_south_bound_summary)))
        )

        onView(withId(androidx.preference.R.id.recycler_view)).check(
            matches(hasDescendant(withText(R.string.pref_east_bound_summary)))
        )

        onView(withId(androidx.preference.R.id.recycler_view)).check(
            matches(hasDescendant(withText(R.string.pref_west_bound_summary)))
        )

        onView(withId(androidx.preference.R.id.recycler_view)).check(
            matches(hasDescendant(withText(R.string.pref_max_quakes_summary)))
        )
    }

}
