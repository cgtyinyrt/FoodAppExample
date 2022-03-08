package com.cagatayinyurt.foodapp.retrofit

import com.cagatayinyurt.foodapp.model.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealAPI {

    @GET("random.php")
    fun getRandomMeal() : Call<MealList>
}