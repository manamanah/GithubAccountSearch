package com.example.android.githubaccountsearch

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation.findNavController
import com.example.android.githubaccountsearch.databinding.ActivityMainBinding
import com.example.android.githubaccountsearch.viewmodels.AccountViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: AccountViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // debugging
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController(this, R.id.nav_host_fragment).navigateUp()
        viewModel.reset()
        super.onOptionsItemSelected(item)
        return true
    }
}
