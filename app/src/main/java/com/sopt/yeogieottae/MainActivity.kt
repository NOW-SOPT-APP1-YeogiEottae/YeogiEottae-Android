package com.sopt.yeogieottae

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sopt.yeogieottae.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val navController = (supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment)
            .findNavController()
        binding.mainBnv.setupWithNavController(navController)

        setupBottomNavigationListener(binding.mainBnv, navController)
        setContentView(binding.root)
    }

    private fun setupBottomNavigationListener(bottomNavigationView: BottomNavigationView, navController: NavController) {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home -> {
                    navigateToFragment(navController, R.id.fragment_home)
                    true
                }
                R.id.fragment_map -> {
                    navigateToFragment(navController, R.id.fragment_map)
                    true
                }
                R.id.fragment_profile -> {
                    navigateToFragment(navController, R.id.fragment_profile)
                    true
                }
                R.id.fragment_like -> {
                    navigateToFragment(navController, R.id.fragment_like)
                    true
                }
                R.id.fragment_search -> {
                    navigateToFragment(navController, R.id.fragment_search)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToFragment(navController: NavController, fragmentId: Int) {
        navController.popBackStack(fragmentId, false)
        navController.navigate(fragmentId)
    }
}
