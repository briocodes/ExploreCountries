package com.briocodes.explorecountries.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.briocodes.explorecountries.R
import com.bumptech.glide.Glide


class ViewPagerAdapter(private val images:List<Int>):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.slider_image)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage=images[position]
        holder.imageView.setImageResource(currentImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}