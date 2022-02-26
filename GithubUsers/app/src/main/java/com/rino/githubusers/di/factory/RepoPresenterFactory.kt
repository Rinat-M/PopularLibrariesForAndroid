package com.rino.githubusers.di.factory

import com.rino.githubusers.ui.repo.RepoPresenter
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RepoPresenterFactory {

    fun presenter(repoUrl: String): RepoPresenter

}