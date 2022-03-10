package com.cagatayinyurt.foodapp.retrofit

import com.cagatayinyurt.foodapp.model.CategoryList
import com.cagatayinyurt.foodapp.model.MealsByCategoryList
import com.cagatayinyurt.foodapp.model.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories() : Call<CategoryList>
}