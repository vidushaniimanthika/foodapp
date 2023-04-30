package com.example.food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food.KotlinCart
import com.example.food.R
import com.example.food.model.DrinkModel

class MyDrinkAdapter(private val drinkModelList: List<DrinkModel>, private val cart: KotlinCart) :
    RecyclerView.Adapter<MyDrinkAdapter.MyDrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDrinkViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_drink_item, parent, false)
        return MyDrinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyDrinkViewHolder, position: Int) {
        val drinkModel = drinkModelList[position]
        holder.text_price.text = "$${drinkModel.price}"
        holder.text_name.text = drinkModel.name

        // Set onClickListener for the add to cart button
        holder.btnAddToCart.setOnClickListener {
            cart.addToCart(drinkModel)
        }
    }

    override fun getItemCount(): Int {
        return drinkModelList.size
    }

    inner class MyDrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_name: TextView
        var text_price: TextView
        var imageView: ImageView
        var btnAddToCart: Button

        init {
            text_name = itemView.findViewById(R.id.textName)
            text_price = itemView.findViewById(R.id.textPrice)
            imageView = itemView.findViewById(R.id.imageView)
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart)
        }

        fun bind(drinkModel: DrinkModel, context: Context) {
            itemView.findViewById<TextView>(R.id.textName).text = drinkModel.name
            itemView.findViewById<TextView>(R.id.textPrice).text = "$${drinkModel.price}"

            // Set the click listener for the button
            btnAddToCart.setOnClickListener {
                // Call the addToCart function and pass the clicked item
                if (context is KotlinCart) {
                    context.addToCart(drinkModel)
                }
            }
        }
    }
}

