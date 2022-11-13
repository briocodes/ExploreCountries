package com.briocodes.explorecountries.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.briocodes.explorecountries.R
import com.briocodes.explorecountries.dataclass.CountryDataItem

class CountryAdapter (private val context: Context, private val countryList:List<CountryDataItem>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){

    inner class CountryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val coutryName: TextView = itemView.findViewById(R.id.country_name)
        val countryCapital: TextView = itemView.findViewById(R.id.country_capital)
        //val countryFlag: ImageView = itemView.findViewById(R.id.country_flag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.coutryName.text = countryList[position].name.common
        holder.countryCapital.text = countryList[position].capital.toString()
        //holder.countryFlag.setImageResource(countryList[position].flag)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}