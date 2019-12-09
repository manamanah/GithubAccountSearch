package com.example.android.githubaccountsearch.views


import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.adapters.GitRepositoryAdapter
import com.example.android.githubaccountsearch.databinding.FragmentAccountBinding
import com.example.android.githubaccountsearch.enums.GitRequestStatus
import com.example.android.githubaccountsearch.viewmodels.AccountViewModel
import kotlin.NullPointerException


class AccountFragment : Fragment() {

    private val inputArg: AccountFragmentArgs by navArgs()

    private lateinit var viewModel: AccountViewModel
    private val recyclerAdapter = GitRepositoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        binding.viewModel = viewModel

        binding.accountName.text = inputArg.accountName

        // display information on error or no account
        viewModel.status.observe(viewLifecycleOwner, Observer{status ->
            // no account with this name
            if (status == GitRequestStatus.ERROR){
                binding.statusDisplay.text = resources.getString(R.string.no_account)

                val errorImage = ContextCompat.getDrawable(
                    activity?.applicationContext ?:
                    throw NullPointerException("Activity was null"), R.drawable.error_image)

                binding.avatarImg.setImageDrawable(errorImage)
            } // no public repos found
            else if (status == GitRequestStatus.SUCCESS && viewModel.repos.value?.size == 0) {
                binding.statusDisplay.text = resources.getString(R.string.no_repos)
            }
            else binding.statusDisplay.visibility = View.GONE
        })

        // set website url for account, if provided
        viewModel.account.observe(viewLifecycleOwner, Observer {account ->
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

        // setup recyclerView
        binding.repositoryRecycler.adapter = recyclerAdapter

        viewModel.repos.observe(viewLifecycleOwner, Observer { list ->
            val tempAdapter = recyclerAdapter as GitRepositoryAdapter
            tempAdapter.updateReposList(list)
        })

        viewModel.getAccount(inputArg.accountName)

        return binding.root
    }
}
