package com.rino.githubusers.di.factory

import com.rino.githubusers.ui.user.UserPresenter
import dagger.assisted.AssistedFactory

@AssistedFactory
interface UserPresenterFactory {

    fun presenter(login: String): UserPresenter

}