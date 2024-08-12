package com.oscargil80.groceryapproommvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class GroceryAdapter(
     var GroceryItemsList: List<GroceryItems>,
    private val onClickDelete: (GroceryItems) -> Unit
    //val onItemSeleted: (Int) -> Unit,
) : RecyclerView.Adapter<GroceryViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GroceryViewModel(layoutInflater.inflate(R.layout.grocery_rv_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: GroceryViewModel, position: Int) {
        val item = GroceryItemsList[position]
        holder.render(item, onClickDelete)
    }

    override fun getItemCount(): Int = GroceryItemsList.size
}
