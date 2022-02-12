package com.rino.githubusers.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.rino.githubusers.App
import com.rino.githubusers.R
import com.rino.githubusers.databinding.FragmentUserBinding
import com.rino.githubusers.model.GithubRepos
import com.rino.githubusers.model.GithubUserDetailed
import com.rino.githubusers.network.GithubApiHolder
import com.rino.githubusers.repository.GithubUsersRepositoryImpl
import com.rino.githubusers.screens.AndroidScreens
import com.rino.githubusers.ui.base.BackButtonListener
import com.rino.githubusers.ui.base.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.*

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER_LOGIN = "USER_ID"

        fun newInstance(login: String) = UserFragment().apply {
            val bundle = Bundle().apply {
                putString(USER_LOGIN, login)
            }

            this.arguments = bundle
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val login = requireArguments().get(USER_LOGIN) as String
        UserPresenter(
            login,
            GithubUsersRepositoryImpl(GithubApiHolder.githubApiService),
            App.instance.router,
            AndroidScreens()
        )
    }
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val imageLoader by lazy { GlideImageLoader() }

    private val circularProgressDrawable by lazy {
        CircularProgressDrawable(requireContext()).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
    }

    private val simpleDateFormat by lazy { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }

    private val reposAdapter by lazy {
        GithubReposAdapter { githubRepos -> presenter.onRepositoryClicked(githubRepos) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.avatarImage.setImageDrawable(circularProgressDrawable)

        with(binding.listOfRepositoriesRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = reposAdapter
        }
    }

    override fun updateUserInfo(user: GithubUserDetailed) {
        with(binding) {
            user.avatarUrl?.let { imageLoader.loadInto(it, avatarImage) }
            name.text = user.name
            location.text = user.location ?: ""
            login.text = String.format(resources.getString(R.string.specific_login), user.login)

            createdAt.text =
                String.format(
                    resources.getString(R.string.specific_created_at),
                    simpleDateFormat.format(user.createdAt)
                )
            publicRepos.text =
                String.format(resources.getString(R.string.specific_public_repos), user.publicRepos)
            followers.text =
                String.format(resources.getString(R.string.specific_followers), user.followers)
            following.text =
                String.format(resources.getString(R.string.specific_following), user.following)
        }
    }

    override fun updateUserRepos(githubRepos: List<GithubRepos>) {
        reposAdapter.submitList(githubRepos)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
}

