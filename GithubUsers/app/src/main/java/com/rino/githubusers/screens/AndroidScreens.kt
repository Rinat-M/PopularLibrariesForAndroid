package com.rino.githubusers.screens

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.rino.githubusers.ui.user.UserFragment
import com.rino.githubusers.ui.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(userId: Long): Screen = FragmentScreen { UserFragment.newInstance(userId) }
}