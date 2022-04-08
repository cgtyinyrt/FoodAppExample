package com.cagatayinyurt.foodapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cagatayinyurt.foodapp.adapter.CategoryMealsAdapter
import com.cagatayinyurt.foodapp.databinding.ActivityCategoryMealsBinding
import com.cagatayinyurt.foodapp.view.fragments.HomeFragment
import com.cagatayinyurt.foodapp.viewmodel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryMealsViewModel: CategoryMealsViewModel
    private lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]

        categoryMealsViewModel.getMealsByCategory(
            intent.getStringExtra(
                HomeFragment.CATEGORY_NAME
            )!!
        )

        categoryMealsViewModel.observeMealsLiveData().observe(this) { mealsList ->
            //Log.d("test", it.strMeal)
            binding.tvCategoryCount.text = mealsList.size.toString()
            categoryMealsAdapter.setMealsList(mealsList)
        }
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvCategoryMeals.apply {
            layoutManager = GridLayoutManager(
                context,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = categoryMealsAdapter
        }
    }
}