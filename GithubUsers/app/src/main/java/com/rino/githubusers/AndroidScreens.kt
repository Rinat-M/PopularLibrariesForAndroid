package com.rino.githubusers

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(userId: Int): Screen = FragmentScreen { UserFragment.newInstance(userId) }
}