package com.oscargil80.groceryapproommvvm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.groceryapproommvvm.databinding.GroceryRvItemBinding

class GroceryViewModel(view: View) : RecyclerView.ViewHolder(view) {

//private  var binding = ItemPaisesBinding.bind(view)
private  var binding = GroceryRvItemBinding.bind(view)


    fun render(
        GroceryItems: GroceryItems,
        onClickDelete: (GroceryItems) -> Unit
    ) {

        binding.tvItemName.text = GroceryItems.itenName.toString()
        binding.tvItemQuantity.text = GroceryItems.itemQuantity.toString()
        binding.tvItemRate.text = "Rs. "+GroceryItems.itemPrice.toString()
        val itemTotal:Int = GroceryItems.itemPrice * GroceryItems.itemQuantity
        binding.tvTotalAmt.text = "Rs."+itemTotal.toString()
            binding.ivDelete.setOnClickListener {
               onClickDelete(GroceryItems)
            }
    }
}