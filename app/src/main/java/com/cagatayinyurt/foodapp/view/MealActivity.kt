package com.cagatayinyurt.foodapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cagatayinyurt.foodapp.R
import com.cagatayinyurt.foodapp.data.local.MealDatabase
import com.cagatayinyurt.foodapp.data.model.Meal
import com.cagatayinyurt.foodapp.databinding.ActivityMealBinding
import com.cagatayinyurt.foodapp.viewmodel.MealViewModel
import com.cagatayinyurt.foodapp.viewmodel.MealViewModelFactory

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink: String
    private lateinit var viewModel: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this)[MealViewModel::class.java]
        val mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        viewModel = ViewModelProvider(this, viewModelFactory)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationInViews()

        loadingCase()

        viewModel.getMealDetail(mealId)
        observeMealDetailsLiveData()

        onYoutubeImageClick()
        onFavoriteClick()
    }

    private fun onFavoriteClick() {
        binding.fabAddToFavorites.setOnClickListener {
            mealToSave?.let {
                viewModel.insertMeal(it)
                Toast.makeText(this, "Meal Saved.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private var mealToSave: Meal? = null
    private fun observeMealDetailsLiveData() {
        viewModel.observeMealDetailLiveData().observe(this) { t ->
            onResponseCase()
            mealToSave = t
            binding.tvCategory.text = "Category: ${t!!.strCategory}"
            binding.tvArea.text = "Area: ${t.strArea}"
            binding.tvInstructionsSteps.text = t.strInstructions

            youtubeLink = t.strYoutube.toString()
        }
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolBar.title = mealName
        binding.collapsingToolBar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
        binding.collapsingToolBar.setExpandedTitleColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.fabAddToFavorites.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.fabAddToFavorites.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}