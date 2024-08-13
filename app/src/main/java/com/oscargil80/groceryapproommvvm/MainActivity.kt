package com.oscargil80.groceryapproommvvm

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscargil80.groceryapproommvvm.databinding.ActivityMainBinding
import com.oscargil80.groceryapproommvvm.databinding.GroceryAddDialogBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var list: List<GroceryItems>
    lateinit var groceryAdapter: GroceryAdapter
    lateinit var groceryViewModal: GroceryViewModal


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<GroceryItems>()
        groceryAdapter = GroceryAdapter(
            list,
            onClickDelete = { grocery -> onClickDelete(grocery) }

        )
        binding.RvGrocery.layoutManager = LinearLayoutManager(this)
        binding.RvGrocery.adapter = groceryAdapter
        val groceryRepository = GroceryRepository(GroceryDataBase(this))
        val factory = GroceryViewModalFactory(groceryRepository)
        groceryViewModal = ViewModelProvider(this, factory).get(GroceryViewModal::class.java)
        groceryViewModal.getAllGroceryItems().observe(this, Observer {
            groceryAdapter.GroceryItemsList = it
            groceryAdapter.notifyDataSetChanged()
        })

        binding.FabGrocery.setOnClickListener {
            openDialog()
        }

    }


    fun openDialog() {

        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.grocery_add_dialog, null )

         val binding = GroceryAddDialogBinding.bind(v)
        val dialog = Dialog(this)
        Log.e("PASA", "Entra aqui ")

        dialog.setContentView(binding.root)


            binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnAdd.setOnClickListener {
            val itemName: String =  binding.etItemName.text.toString()//  itemEdt.text.toString()
            val itemPrice: String =  binding.etItemPrice.text.toString() //itemPriceEdt.text.toString()
            val itemQuantity: String = binding.etItemQuanity.text.toString()//etItemQuanity.text.toString()
            val qty: Int = itemQuantity.toInt()
            val pr: Int = itemPrice.toInt()
            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()) {
                val items = GroceryItems(itemName, qty, pr)
                groceryViewModal.insert(items)
                Toast.makeText(applicationContext, "Item Innserted...", Toast.LENGTH_SHORT).show()
                groceryAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                Toast.makeText(applicationContext, "Please enter all the data ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        dialog.show()
    }

    private fun onClickDelete(grocery: GroceryItems) {
        groceryViewModal.delete(grocery)
        groceryAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Item ${grocery.itenName}  ha sido eliminado ", Toast.LENGTH_SHORT)
            .show();

    }
}