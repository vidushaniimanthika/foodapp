package com.example.food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FoodItemList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_item_list)

        val drink= findViewById<Button>(R.id.drinks)

        drink.setOnClickListener {
            val intent=Intent(this,KotlinCart::class.java)
            startActivity(intent)
        }

    }



}
