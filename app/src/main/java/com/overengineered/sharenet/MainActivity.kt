package com.overengineered.sharenet

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.overengineered.sharenet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navController.navigate(R.id.homeFragment)
                R.id.navigation_requests -> navController.navigate(R.id.requestsFragment)
                R.id.navigation_surplus_items -> navController.navigate(R.id.surplusItemsFragment)
                R.id.navigation_profile -> navController.navigate(R.id.profileFragment)
                else -> false
            }
            true
        }

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navController.navigate(R.id.homeFragment)
                R.id.nav_requests -> navController.navigate(R.id.requestsFragment)
                R.id.nav_surplus_items -> navController.navigate(R.id.surplusItemsFragment)
                R.id.nav_profile -> navController.navigate(R.id.profileFragment)
            }
            binding.drawerLayout.closeDrawers()
            true
        }
    }
}
