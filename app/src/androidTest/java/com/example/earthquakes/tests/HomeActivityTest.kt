package com.example.earthquakes.tests

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.example.earthquakes.R
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.util.getGoogleMapsUrlAt
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_ALL_IDS_VALID
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_NO_DATA
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_TWO_EMPTY
import com.example.earthquakes.parser.BaseMockParser.Companion.EXPECTED_NUM_QUAKES_WHEN_TWO_IDS_ABSENT
import com.example.earthquakes.presentation.home.ui.activity.HomeActivity
import com.example.earthquakes.presentation.home.viewmodel.HomeViewModel
import com.example.earthquakes.presentation.preferences.PrefsActivity
import com.example.earthquakes.setup.base.InstrumentedTestSetup
import com.example.earthquakes.setup.testutil.RecyclerViewItemCountAssertion
import com.example.earthquakes.setup.testutil.RecyclerViewMatcher
import com.example.earthquakes.setup.viewmodel.MockHomeViewModelObject
import io.mockk.every
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(
            HomeActivity::class.java,
            false, false
        )

    private lateinit var mockViewModel: HomeViewModel
    private lateinit var mockItems: List<Quake>

    @Before
    fun doBeforeTest() {
        Intents.init()
        mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
        mockViewModel = MockHomeViewModelObject.getMockHomeViewModel()
        every { mockViewModel.bind() } returns Unit
    }

    @Test
    fun remoteFeedHasAllItemsValid_then_homeListHasExpectedItems() {
        // given
        every { mockViewModel.models } returns MockHomeViewModelObject.models

        // when
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_QUAKES_WHEN_ALL_IDS_VALID)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasSomeIdsAbsent_homeListHasExpectedNumberOfItems() {
        // given
        mockItems = mockParser.getMockQuakesFromFeedWithSomeIdsAbsent()
        every { mockViewModel.models } returns MockHomeViewModelObject.models

        // when
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_QUAKES_WHEN_TWO_IDS_ABSENT)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasAllIdsAbsent_homeListHasNoItems() {
        // given
        mockItems = mockParser.getMockQuakesFromFeedWithAllIdsAbsent()
        every { mockViewModel.models } returns MockHomeViewModelObject.models

        // when
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_QUAKES_WHEN_NO_DATA)
        )
    }

    @Test
    fun remoteFeedHasSomeJsonItemsEmpty_homeListHasExpectedNumberOfItems() {
        // given
        mockItems = mockParser.getMockQuakesFromFeedWithSomeItemsEmpty()
        every { mockViewModel.models } returns MockHomeViewModelObject.models

        // when
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_QUAKES_WHEN_TWO_EMPTY)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasEmptyJsonArray_homeListHasNoItems() {
        // given
        mockItems = mockParser.getMockQuakesFromFeedWithEmptyJsonArray()
        every { mockViewModel.models } returns MockHomeViewModelObject.models

        // when
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_QUAKES_WHEN_NO_DATA)
        )
    }

    @Test
    fun clickOnMenuSettingsItem_opensPrefsActivity() {
        // given
        every { mockViewModel.models } returns MockHomeViewModelObject.models
        launchActivityAndMockLiveData()

        // when
        onView(withId(R.id.action_settings)).perform(click())

        // then
        intended(hasComponent(PrefsActivity::class.java.name))
    }

    @Test
    fun clickOnHomeListItem_opensGoogleMaps() {
        // given
        every { mockViewModel.models } returns MockHomeViewModelObject.models
        val uri = getGoogleMapsUrlAt(mockItems[0].latitude!!, mockItems[0].longitude!!)
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // when
        launchActivityAndMockLiveData()
        clickRecyclerAt(0)
        mDevice.pressBack() // Pressing back to return from Google Maps

        // then
        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(uri)
            )
        )
    }

    private fun clickRecyclerAt(position: Int) {
        onView(withId(R.id.home_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    private fun verifyRecyclerViewShowsExpectedData() {
        mockItems.forEachIndexed { index, item ->
            // scroll to item to make sure it's visible
            onView(withId(R.id.home_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(index))

            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_source_label))
                .check(matches(withText(getString(R.string.vh_source_label))))
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_source))
                .check(matches(withText(item.source)))

            onView(
                withRecyclerView(R.id.home_list).atPositionOnView(
                    index,
                    R.id.txt_datetime_label
                )
            )
                .check(matches(withText(getString(R.string.vh_date_label))))
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_datetime))
                .check(matches(withText(item.datetime)))

            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_depth_label))
                .check(matches(withText(getString(R.string.vh_depth_label))))
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_depth))
                .check(matches(withText(item.depth.toString())))

            onView(
                withRecyclerView(R.id.home_list).atPositionOnView(
                    index,
                    R.id.txt_magnitude_label
                )
            )
                .check(matches(withText(getString(R.string.vh_magnitude_label))))
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_magnitude))
                .check(matches(withText(item.magnitude.toString())))

            onView(
                withRecyclerView(R.id.home_list).atPositionOnView(
                    index,
                    R.id.txt_longitude_label
                )
            )
                .check(matches(withText(getString(R.string.vh_longitude_label))))
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_longitude))
                .check(matches(withText(item.longitude.toString())))

            onView(
                withRecyclerView(R.id.home_list).atPositionOnView(
                    index,
                    R.id.txt_latitude_label
                )
            )
                .check(matches(withText(getString(R.string.vh_latitude_label))))
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_latitude))
                .check(matches(withText(item.latitude.toString())))
        }
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockHomeViewModelObject.mModels.value = mockItems
        }
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun getString(id: Int): String {
        return InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.getString(id)
    }

    @After
    fun doAfterTest() {
        Intents.release()
    }

}
