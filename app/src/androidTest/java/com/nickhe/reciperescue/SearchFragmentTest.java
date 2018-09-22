package com.nickhe.reciperescue;

import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.widget.RelativeLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class SearchFragmentTest {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);
    SearchFragment searchFragment = null;
    private TestActivity testActivity = null;

    /**
     * Prepares looper for activities if it is not prepared
     */
    public SearchFragmentTest() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }


    @Before
    public void setUp() {
        testActivity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        testActivity = null;
        searchFragment = null;
    }

    /**
     * Tests fragment creation and opening.
     */
    @Test
    public void testSetup() {
        RelativeLayout container = testActivity.findViewById(R.id.testContainer);
        assertNotNull(container);

        searchFragment = new SearchFragment();
        assertNotNull(searchFragment);

        testActivity.getSupportFragmentManager().beginTransaction().add(container.getId(), searchFragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();
    }

}