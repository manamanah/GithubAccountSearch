package com.example.android.githubaccountsearch.ui.githubaccount

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.databinding.FragmentGithubAccountBinding
import com.example.android.githubaccountsearch.ui.githubaccount.states.GithubAccountStatus
import com.example.android.githubaccountsearch.ui.githubaccount.states.GithubReposStatus
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class GithubAccountFragment : Fragment() {

    private val inputArg: GithubAccountFragmentArgs by navArgs()
    private val viewModel by viewModel<GithubAccountViewModel> {
        parametersOf(inputArg.accountName)
    }

    private val recyclerAdapter = GitRepositoryAdapter()
    private val languageAdapter = LanguageAdapter()

    private var _binding: FragmentGithubAccountBinding? = null
    private val binding
        get() = _binding!!


    // region lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGithubAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        viewLifecycleOwner.lifecycleScope.launch {
            /**
             * repeatOnLifecycle launches the block in a new coroutine every time the
             * lifecycle is in the STARTED state (or above)
             * and cancels it when it's STOPPED.
             * */
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // => multiple collects need different scopes
                launch {
                    viewModel.accountStatus.collect { accountStatus ->
                        renderAccountStatus(accountStatus)
                    }
                }
                launch {
                    viewModel.githubReposStatus.collect { reposStatus ->
                        renderRepoListStatus(reposStatus)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    // endregion

    // region ui & render methods
    private fun setupUi() {
        with(binding) {
            accountName.text = inputArg.accountName
            repositoryRecycler.adapter = recyclerAdapter
            languageRecycler.adapter = languageAdapter
        }
    }

    private fun renderAccountStatus(githubAccountStatusResponse: GithubAccountStatus) {
        Timber.d("render account status ${githubAccountStatusResponse.javaClass.simpleName}")

        when (githubAccountStatusResponse) {
            is GithubAccountStatus.Loading -> {
                binding.avatarImg.setImageResource(R.drawable.loading_animation)
            }
            is GithubAccountStatus.Success -> {
                binding.statusDisplay.isVisible = false

                // if provided: set website url for account
                val account = githubAccountStatusResponse.account
                if (account.website.isNotBlank()) {
                    val link = HtmlCompat.fromHtml(
                        "<a href='${account.website}'>${account.profileName}</a>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                    binding.accountName.movementMethod = LinkMovementMethod.getInstance()
                    binding.accountName.linksClickable = true
                    binding.accountName.text = link
                }

                if (account.avatar.isEmpty()) {
                    binding.avatarImg.setImageResource(R.drawable.error_image)
                } else {
                    loadImage(binding.avatarImg, account.avatar)
                }
            }
            is GithubAccountStatus.Error -> {
                binding.statusDisplay.text = resources.getString(R.string.no_account)
                binding.avatarImg.setImageResource(R.drawable.error_image)
            }
        }
    }

    private fun renderRepoListStatus(githubReposStatus: GithubReposStatus) {
        Timber.d("render repos list ${githubReposStatus.javaClass.simpleName}")

        when (githubReposStatus) {
            is GithubReposStatus.Loading -> {
                binding.repositoryRecycler.isVisible = false
                binding.languageRecycler.isVisible = false
            }
            is GithubReposStatus.SuccessWithoutRepository -> {
                binding.repositoryRecycler.isVisible = false
                binding.languageRecycler.isVisible = false
                binding.statusDisplay.text = resources.getString(R.string.no_repos)
            }
            is GithubReposStatus.Success -> {
                binding.repositoryRecycler.isVisible = true
                binding.languageRecycler.isVisible = true
                recyclerAdapter.submitList(githubReposStatus.reposList)
                languageAdapter.submitList(githubReposStatus.languages)
            }
            is GithubReposStatus.Error -> {
                binding.repositoryRecycler.isVisible = false
                binding.languageRecycler.isVisible = false
            }
        }
    }

    private fun loadImage(view: ImageView, src: String) {
        Timber.d("load image $src")

        /**
         * Glide automatically clears the load and recycles any used load-resources
         * if activity or fragment passed in Glide.with() is destroyed
         */
        Glide.with(this)
            .load(src)
            .into(view)
    }
    // endregion
}
