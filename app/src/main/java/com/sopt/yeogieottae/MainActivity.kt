package com.sopt.yeogieottae

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sopt.yeogieottae.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment)
                .findNavController()
        binding.mainBnv.setupWithNavController(navController)
        setContentView(binding.root)
    }
}