package com.rino.githubusers

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(userId: Int): Screen
}

