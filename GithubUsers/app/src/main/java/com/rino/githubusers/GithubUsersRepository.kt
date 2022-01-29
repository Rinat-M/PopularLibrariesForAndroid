package com.rino.githubusers

class GithubUsersRepository {
    private val repositories = (1..50).map {
        GithubUser(it, "User $it")
    }

    fun getUsers(): List<GithubUser> {
        return repositories
    }

    fun getUserById(id: Int): GithubUser? = repositories.firstOrNull { it.id == id }
}

