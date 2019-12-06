package com.example.android.githubaccountsearch.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs

import com.example.android.githubaccountsearch.R
import kotlinx.android.synthetic.main.fragment_account.view.*


class AccountFragment : Fragment() {

    private val inputArg: AccountFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // adapt actionbar
        val supportActionBar = (activity as AppCompatActivity?)?.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view.account_name.text = inputArg.accountName

        return view
    }


}
