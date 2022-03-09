package com.cagatayinyurt.foodapp.retrofit

import com.cagatayinyurt.foodapp.model.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("random.php")
    fun getRandomMeal() : Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String) : Call<MealList>
}