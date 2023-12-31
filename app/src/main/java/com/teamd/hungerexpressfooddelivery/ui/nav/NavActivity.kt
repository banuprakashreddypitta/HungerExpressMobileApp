package com.teamd.hungerexpressfooddelivery.ui.nav

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.teamd.hungerexpressfooddelivery.R
import com.teamd.hungerexpressfooddelivery.databinding.ActivityNavBinding
import com.teamd.hungerexpressfooddelivery.utils.AppPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavActivity : AppCompatActivity() {
    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity,NavActivity::class.java))
            activity.finish()
        }
    }
    private lateinit var binding:ActivityNavBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val isAdmin = AppPreferences.userType == "admin"

        binding.bottomNavigationView.menu.apply {
            findItem(R.id.restaurantFragment).isVisible = isAdmin
            findItem(R.id.ordersFragment).isVisible = !isAdmin
            findItem(R.id.cartFragment).isVisible = !isAdmin
        }

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}