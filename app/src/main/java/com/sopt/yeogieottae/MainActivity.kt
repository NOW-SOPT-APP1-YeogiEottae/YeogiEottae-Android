package com.sopt.yeogieottae

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    private fun setNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            setupNavigation(navController)
        } else {
            Snackbar.make(binding.root, "NavigationError", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupNavigation(navController: NavController) {
        binding.mainBnv.setupWithNavController(navController)
        hideBottomNavigationView(navController)
    }

    private fun hideBottomNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.mainBnv.visibility = when (destination.id) {
                R.id.fragment_compare -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
}