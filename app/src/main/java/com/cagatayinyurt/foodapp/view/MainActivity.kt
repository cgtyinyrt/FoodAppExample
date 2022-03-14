package com.cagatayinyurt.foodapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cagatayinyurt.foodapp.R
import com.cagatayinyurt.foodapp.data.local.MealDatabase
import com.cagatayinyurt.foodapp.viewmodel.HomeViewModel
import com.cagatayinyurt.foodapp.viewmodel.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(mealDatabase)
        ViewModelProvider(this, homeViewModelProviderFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

//    override fun onStart() {
//        super.onStart()
//        setUpBottomNavigationMenu()
//    }
//
//    private fun setUpBottomNavigationMenu() {
//        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val navController = findNavController(R.id.fragmentContainerView)
//
//        NavigationUI.setupWithNavController(bottomNavigation, navController)
//    }
}