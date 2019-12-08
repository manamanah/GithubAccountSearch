package com.example.android.githubaccountsearch.views


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.android.githubaccountsearch.R
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_input.view.*


class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        // adapt actionbar
        val supportActionBar = (activity as AppCompatActivity?)?.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        view.input_rootlayout.setOnClickListener {
            closeKeyboard()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        submit_button.setOnClickListener{
            if (input.text?.trim().isNullOrEmpty()){
                Toast.makeText(activity, context?.getString(R.string.invalid_input), Toast.LENGTH_LONG). show()
            }
            else {
                val profileName = input.text.toString().trim()
                input.text = null

                closeKeyboard()

                findNavController().navigate(InputFragmentDirections.actionInputFragmentToAccountFragment(profileName))
            }
        }
    }

    private fun closeKeyboard(){
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
