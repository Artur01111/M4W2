package com.example.m4w2.ui.activity

import com.example.m4w2.databinding.ActivityMainBinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.m4w2.R
import com.example.m4w2.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)as NavHostFragment
        navController= navHostFragment.navController

        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
        if (sharedPreferences.isOnBoardShown) {
            navController.navigate(R.id.action_onBoardFragment_to_singUpFragment)
        } else if (!sharedPreferences.isOnBoardShown) {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}