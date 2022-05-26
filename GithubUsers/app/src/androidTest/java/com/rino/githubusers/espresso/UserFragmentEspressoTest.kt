package com.rino.githubusers.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rino.githubusers.R
import com.rino.githubusers.ui.user.UserFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserFragmentEspressoTest {

    @Test
    fun fragment_testBundle() {
        val testLogin = "mojombo"
        val fragmentArgs = bundleOf("USER_ID" to testLogin)
        val scenario = launchFragmentInContainer<UserFragment>(fragmentArgs, R.style.Theme_GithubUsers)
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = matches(withText("@$testLogin"))
        onView(withId(R.id.login)).check(assertion)
    }
    
}