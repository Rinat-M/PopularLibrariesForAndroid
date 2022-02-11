package com.rino.githubusers.ui.base

interface ImageLoader<T> {
    fun loadInto(url: String, container: T)
}