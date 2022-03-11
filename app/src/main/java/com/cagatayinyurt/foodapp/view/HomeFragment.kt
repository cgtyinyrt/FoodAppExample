package com.cagatayinyurt.foodapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cagatayinyurt.foodapp.adapter.CategoriesAdapter
import com.cagatayinyurt.foodapp.adapter.PopularMealAdapter
import com.cagatayinyurt.foodapp.databinding.FragmentHomeBinding
import com.cagatayinyurt.foodapp.model.Meal
import com.cagatayinyurt.foodapp.model.MealsByCategory
import com.cagatayinyurt.foodapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: PopularMealAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val MEAL_ID = "com.cagatayinyurt.foodapp.view.idMeal"
        const val MEAL_NAME = "com.cagatayinyurt.foodapp.view.nameMeal"
        const val MEAL_THUMB = "com.cagatayinyurt.foodapp.view.thumbMeal"
        const val CATEGORY_NAME = "com.cagatayinyurt.foodapp.view.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        popularItemsAdapter = PopularMealAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()

        viewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

        viewModel.getPopularItems()
        observePopularMeal()
        onPopularItemClick()

        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategoriesLiveData()

        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(
                context,
                3,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setCategoryList(categories)
        }
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick = { meal ->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerView() {
        binding.rvMealsPopular.apply {
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = popularItemsAdapter
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)

            this.randomMeal = meal
        }
    }

    private fun observePopularMeal() {
        viewModel.observePopularMealLiveData().observe(viewLifecycleOwner) { mealList ->
            popularItemsAdapter.setMeals(
                mealsByCategoryList = mealList as ArrayList<MealsByCategory>
            )
        }
    }
}