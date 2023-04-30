package com.example.food.listener

import android.view.View
import com.example.food.model.DrinkModel

interface DrinkLodListner {

    fun onDrinkLoadSuccess(drinkLodListner: List<DrinkModel>)
    fun onDrinkLoadFaild(message: String?, mainLayout: View)
    abstract fun onDrinkLoadFaild(message: String)
}