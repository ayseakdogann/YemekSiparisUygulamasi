package com.example.yemeksiparisuygulamasi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                ?.findNavController()

            when (item.itemId) {
                R.id.homeFragment -> {
                    navController?.navigate(R.id.homeFragment)
                    true
                }

                R.id.cartFragment -> {
                    navController?.navigate(R.id.cartFragment)
                    true
                }

                else -> false
            }
        }

    }}