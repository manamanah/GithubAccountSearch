package com.example.android.githubaccountsearch.views


import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.databinding.FragmentAccountBinding
import com.example.android.githubaccountsearch.viewmodels.AccountViewModel


class AccountFragment : Fragment() {

    private val inputArg: AccountFragmentArgs by navArgs()

    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
        binding.viewModel = viewModel

        binding.accountName.text = inputArg.accountName

        viewModel.account.observe(viewLifecycleOwner, Observer {account ->

            if (account.website.isNotEmpty()){
                val link = HtmlCompat.fromHtml("<a href='${account.website}'>${account.profileName}</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                binding.accountName.movementMethod = LinkMovementMethod.getInstance()
                binding.accountName.linksClickable = true
                binding.accountName.text = link
            }
        })

        // adapt actionbar
        val supportActionBar = (activity as AppCompatActivity?)?.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getAccount(inputArg.accountName)

        return binding.root
    }
}
