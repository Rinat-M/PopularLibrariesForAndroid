package com.rino.githubusers.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rino.githubusers.*
import com.rino.githubusers.databinding.FragmentUsersBinding
import com.rino.githubusers.model.GithubUser
import com.rino.githubusers.network.GithubApiHolder
import com.rino.githubusers.repository.GithubUsersRepositoryImpl
import com.rino.githubusers.screens.AndroidScreens
import com.rino.githubusers.ui.base.BackButtonListener
import com.rino.githubusers.ui.base.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepositoryImpl(GithubApiHolder.githubApiService),
            App.instance.router,
            AndroidScreens()
        )
    }
    private val usersAdapter by lazy {
        UsersAdapter(GlideImageLoader()) { githubUser -> presenter.onUserClicked(githubUser) }
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.usersRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = usersAdapter
        }
    }

    override fun updateList(users: List<GithubUser>) {
        usersAdapter.submitList(users)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

