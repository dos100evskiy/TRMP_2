package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HotelsAdapter(var hotels: List<Hotel>, var context: Context) : RecyclerView.Adapter<HotelsAdapter.MyViewHolder>() {

    class MyViewHolder(view : View) :RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleInList)
        val location: TextView = view.findViewById(R.id.locationInList)
        val rating: TextView = view.findViewById(R.id.ratingInList)
        val price: TextView = view.findViewById(R.id.priceInList)
        val img: ImageView = view.findViewById(R.id.imageInList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hotels.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = hotels[position].title
        holder.location.text = hotels[position].location
        holder.rating.text = hotels[position].price.toString()
        holder.price.text = hotels[position].price.toString()
        Picasso.get().load(hotels[position].img).into(holder.img)

    }
}