package com.rino.githubusers.ui.user

import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.core.model.GithubUserDetailed
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun updateUserInfo(user: GithubUserDetailed)
    fun updateUserRepos(githubRepos: List<GithubRepos>)
}