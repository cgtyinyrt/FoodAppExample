package com.cagatayinyurt.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cagatayinyurt.foodapp.data.local.MealDatabase

@Suppress("UNCHECKED_CAST")
class MealViewModelFactory(
    private val mealDatabase: MealDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(mealDatabase) as T
    }
}

