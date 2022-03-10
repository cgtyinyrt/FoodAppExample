package com.cagatayinyurt.foodapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cagatayinyurt.foodapp.databinding.CategoryItemBinding
import com.cagatayinyurt.foodapp.model.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoriesList = ArrayList<Category>()

    inner class CategoriesViewHolder(
        val binding: CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)

        holder.binding.tvCategoryName.text = categoriesList[position].strCategory
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoryList(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }
}