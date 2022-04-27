package com.rino.githubusers

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.repository.GithubUsersRepository
import com.rino.githubusers.screens.IScreens
import com.rino.githubusers.ui.users.UsersPresenter
import com.rino.githubusers.ui.users.UsersView
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screens: IScreens

    @Mock
    private lateinit var usersRepository: GithubUsersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(router, screens, usersRepository)
    }

    @Test
    fun should_LoadData_After_OnFirstViewAttach() {
        val users = listOf(mock<GithubUser>())
        whenever(usersRepository.getUsers()).thenReturn(Single.just(users))

        val view = mock<UsersView>()
        presenter.attachView(view)
        verify(usersRepository, times(1)).getUsers()
    }

    @Test
    fun should_UpdateList_After_LoadDataWithoutErrors() {
        val users = listOf(mock<GithubUser>())
        whenever(usersRepository.getUsers()).thenReturn(Single.just(users))

        val view = mock<UsersView>()
        presenter.attachView(view)
        verify(view, times(1)).updateList(users)
    }

    @Test
    fun should_ShowMessage_After_LoadDataWithError() {
        val error = RuntimeException("Test error")
        whenever(usersRepository.getUsers()).thenReturn(Single.fromCallable { throw error })

        val view = mock<UsersView>()
        presenter.attachView(view)
        verify(view, times(1)).showMessage(error.message.toString())
    }

    @Test
    fun should_Exit_When_BackPressed() {
        presenter.backPressed()
        verify(router, times(1)).exit()
    }

    @Test
    fun should_NavigateToUserScreen_When_OnUserClicked() {
        val user = mock<GithubUser>()
        presenter.onUserClicked(user)
        verify(router, times(1)).navigateTo(screens.user(user.login))
    }
}