package com.rino.githubusers.espresso

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rino.githubusers.R
import com.rino.githubusers.ui.main.MainActivity
import com.rino.githubusers.ui.users.UsersAdapter
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRecyclerViewTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun scrollTo() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.users_recycler_view))
            .perform(
                RecyclerViewActions.scrollTo<UsersAdapter.UserViewHolder>(
                    hasDescendant(withText("mojodna"))
                )
            )
    }

    @Test
    fun performClickAtPosition() {
        onView(isRoot()).perform(delay())
        onView(withId(R.id.users_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.UserViewHolder>(
                    10,
                    click()
                )
            )
    }

    private fun delay(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}