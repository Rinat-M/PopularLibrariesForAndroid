package com.rino.githubusers.ui.repo

import android.os.Bundle
import com.rino.githubusers.App
import com.rino.githubusers.R
import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.databinding.FragmentReposBinding
import com.rino.githubusers.ui.base.BackButtonListener
import com.rino.githubusers.ui.base.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.*

class RepoFragment : MvpAppCompatFragment(R.layout.fragment_repos), RepoView, BackButtonListener {

    companion object {
        private const val REPO_URL = "REPO_URL"

        fun newInstance(url: String): RepoFragment {
            return RepoFragment().apply {
                arguments = Bundle().apply {
                    putString(REPO_URL, url)
                }
            }
        }
    }

    private val binding: FragmentReposBinding by viewBinding()

    private val presenter by moxyPresenter {
        val repoUrl = requireArguments().get(REPO_URL) as String
        App.appDependencyGraph.reposSubcomponent.repoPresenterFactory().presenter(repoUrl)
    }

    private val simpleDateFormat by lazy { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }

    override fun updateRepoInfo(githubRepos: GithubRepos) {
        with(binding) {
            name.text = String.format(resources.getString(R.string.specific_name), githubRepos.name)

            fullName.text = String.format(
                resources.getString(R.string.specific_full_name),
                githubRepos.fullName
            )

            createdAt.text = String.format(
                resources.getString(R.string.specific_created_at),
                simpleDateFormat.format(githubRepos.createdAt)
            )

            repoPrivate.text =
                String.format(resources.getString(R.string.specific_private), githubRepos.private)

            fork.text = String.format(resources.getString(R.string.specific_fork), githubRepos.fork)

            watchersCount.text = String.format(
                resources.getString(R.string.specific_watchers),
                githubRepos.watchersCount
            )

            forksCount.text = String.format(
                resources.getString(R.string.specific_forks),
                githubRepos.forksCount
            )
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()

}
