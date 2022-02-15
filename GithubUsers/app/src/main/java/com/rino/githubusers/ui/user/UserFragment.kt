package com.rino.githubusers.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.rino.githubusers.App
import com.rino.githubusers.R
import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.core.model.GithubUserDetailed
import com.rino.githubusers.databinding.FragmentUserBinding
import com.rino.githubusers.ui.base.BackButtonListener
import com.rino.githubusers.ui.base.ImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

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

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    private val presenter by moxyPresenter {
        val login = requireArguments().get(USER_LOGIN) as String
        App.instance.appComponent.providesUserPresenterFactory().presenter(login)
    }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
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

            user.createdAt?.let {
                createdAt.text = String.format(
                    resources.getString(R.string.specific_created_at),
                    simpleDateFormat.format(it)
                )
            }

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

