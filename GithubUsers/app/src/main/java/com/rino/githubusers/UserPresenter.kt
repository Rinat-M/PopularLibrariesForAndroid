package com.rino.githubusers

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val userId: Int,
    private val usersRepository: GithubUsersRepository,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        val user = usersRepository.getUserById(userId)
        viewState.updateView(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
