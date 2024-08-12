package com.oscargil80.groceryapproommvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscargil80.groceryapproommvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var list: List<GroceryItems>
    lateinit var groceryAdapter: GroceryAdapter
    lateinit var groceryViewModal: GroceryViewModal


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList<GroceryItems>()
        groceryAdapter = GroceryAdapter(
            list,
            onClickDelete = { grocery -> onClickDelete(grocery)}
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

    }

    private fun onClickDelete(grocery: GroceryItems) {

    }
}