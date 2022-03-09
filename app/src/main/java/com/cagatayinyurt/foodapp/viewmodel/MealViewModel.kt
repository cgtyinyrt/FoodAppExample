package com.cagatayinyurt.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cagatayinyurt.foodapp.model.Meal
import com.cagatayinyurt.foodapp.model.MealList
import com.cagatayinyurt.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel : ViewModel() {

    private var mealDetailLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    mealDetailLiveData.value = response.body()!!.meals[0]
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("Meal Activity", t.message.toString())
            }
        })
    }

    fun observeMealDetailLiveData(): LiveData<Meal> {
        return mealDetailLiveData
    }
}