package com.rino.githubusers.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.rino.githubusers.ui.repo.RepoFragment
import com.rino.githubusers.ui.user.UserFragment
import com.rino.githubusers.ui.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(login: String) = FragmentScreen { UserFragment.newInstance(login) }
    override fun userRepository(url: String) = FragmentScreen { RepoFragment.newInstance(url) }
}