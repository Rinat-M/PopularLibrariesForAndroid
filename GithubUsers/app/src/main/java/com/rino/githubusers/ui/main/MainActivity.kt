package com.rino.githubusers.ui.main

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.rino.githubusers.App
import com.rino.githubusers.R
import com.rino.githubusers.ui.base.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { App.appDependencyGraph.appComponent.mainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appDependencyGraph.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}

