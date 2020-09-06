package com.example.android.githubaccountsearch.views

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)

        // adapt actionbar
        val supportActionBar = (activity as? AppCompatActivity)?.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.inputRootlayout.setOnClickListener {
            closeKeyboard()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val profileName = binding.input.text?.toString()?.trim().orEmpty()

            if (!isValidInput(profileName)){
                Toast.makeText(
                    activity,
                    context?.getString(R.string.invalid_input),
                    Toast.LENGTH_LONG
                ). show()
            }
            else {
                binding.input.text = null
                closeKeyboard()

                findNavController().navigate(
                    InputFragmentDirections.actionInputFragmentToAccountFragment(profileName)
                )
            }
        }
    }

    private fun isValidInput(input: String): Boolean {
        return !(input.isEmpty() || input.any {
                    !(it.isLetter() || it.isDigit() || it == '-')
                })
    }

    override fun onDestroyView() {
        binding.submitButton.setOnClickListener(null)
        binding.inputRootlayout.setOnClickListener(null)

        _binding = null

        super.onDestroyView()
    }

    private fun closeKeyboard(){
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputManager?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
