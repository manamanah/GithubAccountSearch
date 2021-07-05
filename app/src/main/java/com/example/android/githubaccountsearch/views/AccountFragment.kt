package com.example.android.githubaccountsearch.views

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.adapters.GitRepositoryAdapter
import com.example.android.githubaccountsearch.adapters.LanguageAdapter
import com.example.android.githubaccountsearch.databinding.FragmentAccountBinding
import com.example.android.githubaccountsearch.enums.GitRequestStatus
import com.example.android.githubaccountsearch.viewmodels.AccountViewModel


class AccountFragment : Fragment() {

    private val inputArg: AccountFragmentArgs by navArgs()
    private val viewModel: AccountViewModel by viewModels()

    private val recyclerAdapter = GitRepositoryAdapter()
    private val languageAdapter = LanguageAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.accountName.text = inputArg.accountName

        // display information on error or no account
        viewModel.status.observe(viewLifecycleOwner, Observer{status ->

            // no account with this name
            if (status == GitRequestStatus.ERROR){
                binding.statusDisplay.text = resources.getString(R.string.no_account)
                binding.avatarImg.setImageResource(R.drawable.error_image)
            }
            else if (status == GitRequestStatus.SUCCESS)
                binding.statusDisplay.visibility = View.GONE
        })

        // display info for repo search
        viewModel.repoStatus.observe(viewLifecycleOwner, Observer { status ->
            val repoSize = viewModel.repos.value?.size ?: -1

            if (status != GitRequestStatus.LOADING && repoSize <= 0){
                binding.statusDisplay.text =
                    resources.getString(R.string.no_repos)
            }
        })

        // set website url for account, if provided
        viewModel.account.observe(viewLifecycleOwner, Observer { account ->
            if (account != null && account.website.isNotEmpty()){
                val link = HtmlCompat.fromHtml("<a href='${account.website}'>${account.profileName}</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.accountName.movementMethod = LinkMovementMethod.getInstance()
                binding.accountName.linksClickable = true
                binding.accountName.text = link
            }
        })

        // adapt actionbar
        val supportActionBar = (activity as AppCompatActivity?)?.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // setup recyclerView for git-repos
        binding.repositoryRecycler.adapter = recyclerAdapter

        // setup recyclerView for languages
        binding.languageRecycler.adapter = languageAdapter

        viewModel.repos.observe(viewLifecycleOwner, Observer { list ->
            recyclerAdapter.updateReposList(list)
        })

        viewModel.languages.observe(viewLifecycleOwner, Observer { list ->
            languageAdapter.updateList(list)
        })

        viewModel.getAccount(inputArg.accountName)

        return binding.root
    }
}
