package com.rino.githubusers.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import com.rino.githubusers.App
import com.rino.githubusers.core.cache.GithubUsersCacheImpl
import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.core.repository.GithubUsersRepositoryImpl
import com.rino.githubusers.databinding.FragmentUsersBinding
import com.rino.githubusers.network.GithubApiHolder
import com.rino.githubusers.network.NetworkStatus
import com.rino.githubusers.screens.AppScreens
import com.rino.githubusers.ui.base.BackButtonListener
import com.rino.githubusers.ui.base.GlideImageCacheLoader
import com.rino.githubusers.ui.showToast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    @Inject
    lateinit var router: Router

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepositoryImpl(
                GithubUsersCacheImpl(
                    NetworkStatus(requireContext()),
                    GithubApiHolder.githubApiService,
                    App.instance.database.userDao
                )
            ),
            router,
            AppScreens()
        )
    }

    private val usersAdapter by lazy {
        UsersAdapter(
            GlideImageCacheLoader(App.instance.database.imagesDao),
            presenter::onUserClicked
        )
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

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

    override fun showMessage(message: String) {
        context?.showToast(message)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

