package com.rino.githubusers.ui.repo

import com.rino.githubusers.core.model.GithubRepos
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoView : MvpView {
    fun updateRepoInfo(githubRepos: GithubRepos)
}