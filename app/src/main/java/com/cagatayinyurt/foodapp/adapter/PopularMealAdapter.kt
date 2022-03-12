package com.cagatayinyurt.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cagatayinyurt.foodapp.databinding.PopularItemsBinding
import com.cagatayinyurt.foodapp.data.model.MealsByCategory

class PopularMealAdapter : RecyclerView.Adapter<PopularMealAdapter.PopularMealHolder>() {

    private var mealsList = ArrayList<MealsByCategory>()
    lateinit var onItemClick: ((MealsByCategory) -> Unit)

    inner class PopularMealHolder(
        val binding: PopularItemsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealHolder {
        return PopularMealHolder(
            PopularItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMealHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMeals(mealsByCategoryList: ArrayList<MealsByCategory>) {
        this.mealsList = mealsByCategoryList
        notifyDataSetChanged()
    }
}