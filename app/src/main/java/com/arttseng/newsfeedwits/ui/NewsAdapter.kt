package com.arttseng.newsfeedwits.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arttseng.newsfeedwits.R
import com.arttseng.newsfeedwits.data.NewsBean



class DataAdapter(private var mData: List<NewsBean>, var onItemClick: ((NewsBean) -> Unit)? = null) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_newslist, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = mData[position]
        holder.dataView.text = bean.date +"\n"+ bean.time
        holder.title.text = bean.title
        holder.subtitle.text = bean.subtitle
        if(bean.isRead)
            holder.parent.setBackgroundColor(Color.LTGRAY)
        else
            holder.parent.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    fun update(newData: List<NewsBean>) {
        mData = newData
        notifyDataSetChanged()
    }


    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val parent = v
        val dataView: TextView = v.findViewById(R.id.tv_datetime)
        val title: TextView = v.findViewById(R.id.tv_title)
        val subtitle: TextView = v.findViewById(R.id.tv_subtitle)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }
    }

}