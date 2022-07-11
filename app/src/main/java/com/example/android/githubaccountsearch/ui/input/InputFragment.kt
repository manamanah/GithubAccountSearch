package com.example.android.githubaccountsearch.ui.input

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.databinding.FragmentInputBinding
import timber.log.Timber


class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding
        get() = _binding!!

    // region lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.inputRootlayout.setOnClickListener { closeKeyboard() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val profileName = binding.input.text?.toString()?.trim().orEmpty()
            Timber.d("provided profile name $profileName")

            if (!isValidInput(profileName)) {
                binding.input.error = resources.getString(R.string.invalid_input_edittext)
                Toast.makeText(
                    activity,
                    context?.getString(R.string.invalid_input),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.input.text = null
                closeKeyboard()

                findNavController().navigate(
                    InputFragmentDirections.actionInputFragmentToAccountFragment(profileName)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    // endregion

    private fun isValidInput(input: String): Boolean {
        return input.isNotEmpty() && !input.any {
            !(it.isLetter() || it.isDigit() || it == '-')
        }
    }

    private fun closeKeyboard() {
        val inputManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputManager?.hideSoftInputFromWindow(
            activity?.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}
