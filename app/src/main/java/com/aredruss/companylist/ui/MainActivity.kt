package com.aredruss.companylist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.aredruss.companylist.R
import com.aredruss.companylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            val navHostController =
                (supportFragmentManager.findFragmentById(R.id.nav_host_fr) as NavHostFragment)
                    .navController
        }
    }
}