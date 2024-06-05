package com.mitroshenko.mynotes.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.mynotes.R

class MyAdapter(listMain: ArrayList<String>) : RecyclerView.Adapter <MyAdapter.MyHolder>() {
    var listArray = listMain

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        fun setData(title: String){
            tvTitle.text = title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.rc_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }
    fun updateAdapter(listItems: List<String>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

}