package com.example.food

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.food.adapter.MyDrinkAdapter
import com.example.food.model.DrinkModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.recyclerview.widget.LinearLayoutManager


class KotlinCart : AppCompatActivity(), DrinkLoadListener {

    private lateinit var context: Context
    private lateinit var mainLayout: View
    private lateinit var recycler_drink: RecyclerView

    private var drinkLoadListener: DrinkLoadListener = this
    private var cart: MutableList<DrinkModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_cart)
        context = this
        mainLayout = findViewById(R.id.main_layout)
        recycler_drink = findViewById(R.id.recycler_drink)
        init()
        loadDrinkFromFirebase()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(this)
        recycler_drink.layoutManager = layoutManager

        val adapter = MyDrinkAdapter(cart, this)
        recycler_drink = findViewById(R.id.recycler_drink) // initialize recycler_drink
        recycler_drink.adapter = adapter
    }

    private fun loadDrinkFromFirebase() {
        val drinkModelList: MutableList<DrinkModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("Drink")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.exists()) {
                        for (drinkSnapshot in snapshot.children) {
                            val drinkModel = drinkSnapshot.getValue(DrinkModel::class.java)
                            drinkModel!!.key = drinkSnapshot.key
                            drinkModelList.add(drinkModel)
                        }
                        drinkLoadListener.onDrinkLoadSuccess(drinkModelList)
                    } else
                        drinkLoadListener.onDrinkLoadFaild("Drink Item Not exists", mainLayout)

                }

                override fun onCancelled(error: DatabaseError) {
                    drinkLoadListener.onDrinkLoadFaild(error.message, mainLayout)
                }
            })
    }

    override fun onDrinkLoadSuccess(drinkModelList: List<DrinkModel>) {
        cart.clear()
        cart.addAll(drinkModelList)
    }

    override fun onDrinkLoadFaild(message: String?, mainLayout: View) {
        Snackbar.make(mainLayout, message!!, Snackbar.LENGTH_LONG).show()
    }

    override fun onDrinkLoadFaild(message: String) {
        Snackbar.make(mainLayout, message!!, Snackbar.LENGTH_LONG).show()
    }

    fun addToCart(item: DrinkModel) {
        cart.add(item)
        Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()
    }
}

interface DrinkLoadListener {
    fun onDrinkLoadSuccess(drinkModelList: List<DrinkModel>)
    fun onDrinkLoadFaild(message: String?, mainLayout: View)
    fun onDrinkLoadFaild(message: String)
}
