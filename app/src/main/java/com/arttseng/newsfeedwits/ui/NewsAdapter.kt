package com.arttseng.newsfeedwits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.arttseng.newsfeedwits.data.NewsBean



class DataAdapter(private var mData: List<NewsBean>) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dataView.text = mData[position].date +"\n"+ mData[position].time
        holder.title.text = mData[position].title
        holder.subtitle.text = mData[position].subtitle
        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, "Item $position is clicked.看明細 ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    fun update(newData: List<NewsBean>) {
        mData = newData
        notifyDataSetChanged()
    }


    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val dataView: TextView = v.findViewById(R.id.tv_datetime)
        val title: TextView = v.findViewById(R.id.tv_title)
        val subtitle: TextView = v.findViewById(R.id.tv_subtitle)
    }

}