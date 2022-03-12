package com.cagatayinyurt.foodapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cagatayinyurt.foodapp.data.model.Meal

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM mealInformation ")
    fun getAllMeals(): LiveData<List<Meal>>
}