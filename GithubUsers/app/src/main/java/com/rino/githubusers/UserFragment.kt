package com.rino.githubusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rino.githubusers.databinding.FragmentUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val USER_ID = "USER_ID"

        fun newInstance(userId: Int) = UserFragment().apply {
            val bundle = Bundle().apply {
                putInt(USER_ID, userId)
            }

            this.arguments = bundle
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val userId = arguments?.get(USER_ID) as Int
        UserPresenter(userId, GithubUsersRepository(), App.instance.router)
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

    override fun updateView(user: GithubUser?) {
        viewBinding.login.text = user?.login
    }

    override fun backPressed() = presenter.backPressed()
}

