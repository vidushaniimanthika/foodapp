package com.example.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.auth.User

class MyAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.layout_drink_item,parent,false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.mango.text
        holder.mangoj.text
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val mango : TextView= itemView.findViewById(R.id.mango)
        val mangoj :TextView=itemView.findViewById(R.id.mango)


    }

}