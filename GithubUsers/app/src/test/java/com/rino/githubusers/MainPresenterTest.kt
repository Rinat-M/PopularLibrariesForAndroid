package com.rino.githubusers

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.rino.githubusers.screens.IScreens
import com.rino.githubusers.ui.main.MainPresenter
import com.rino.githubusers.ui.main.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var  screens: IScreens

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(router, screens)
    }

    @Test
    fun should_ReplaceScreen_After_OnFirstViewAttach() {
        val view = mock<MainView>()
        presenter.attachView(view)
        verify(router, times(1)).replaceScreen(screens.users())
    }

    @Test
    fun should_Exit_When_BackClicked() {
        presenter.backClicked()
        verify(router, times(1)).exit()
    }
}