package com.example.myapplication

import SharedPreferencesHotels
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HotelsAdapterCart(var hotels: MutableList<Hotel>, var context: Context,val updateUI: () -> Unit) : RecyclerView.Adapter<HotelsAdapterCart.MyViewHolder>() {

    class MyViewHolder(view : View) :RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleInListCart)
        val price: TextView = view.findViewById(R.id.priceInListCart)
        val img: ImageView = view.findViewById(R.id.imageInListCart)

        val btn: Button = view.findViewById(R.id.buttonInListCart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_in_list_cart, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hotels.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = hotels[position].title
        holder.price.text = "${hotels[position].price.toString()} Р"
        Picasso.get().load(hotels[position].img).into(holder.img)

        holder.btn.setOnClickListener {
            SharedPreferencesHotels.removeHotel(context ,hotels[position])
            hotels.removeAt(position)
            notifyItemRemoved(position)

            updateUI()
        }
    }
}