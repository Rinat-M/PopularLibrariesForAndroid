package com.rino.githubusers.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rino.githubusers.App
import com.rino.githubusers.databinding.FragmentUserBinding
import com.rino.githubusers.model.GithubUser
import com.rino.githubusers.network.GithubApiHolder
import com.rino.githubusers.repository.GithubUsersRepositoryImpl
import com.rino.githubusers.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

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
            App.instance.router
        )
    }
    private var _viewBinding: FragmentUserBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentUserBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    override fun updateView(user: GithubUser) {
        viewBinding.login.text = user.login
    }

    override fun backPressed() = presenter.backPressed()
}

