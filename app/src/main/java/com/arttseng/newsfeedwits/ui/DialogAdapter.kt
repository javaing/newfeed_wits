package com.arttseng.newsfeedwits.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.arttseng.newsfeedwits.R
import com.arttseng.newsfeedwits.data.ProviderBean

class DialogAdapter : RecyclerView.Adapter<DialogAdapter.MyViewHolder>() {

        interface GetDataCallBack {
            fun getDataChange(data: List<ProviderBean>)
        }

        fun setDataCallBack(getDataCallBack: GetDataCallBack) {
            this.getDataCallBack = getDataCallBack
        }

        private var data = listOf<ProviderBean>()
        lateinit var getDataCallBack: GetDataCallBack


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_dialog, parent, false)
            )

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val it = data[position]
            holder.itemCheck.text = data[position].name
            holder.itemCheck.isChecked = data[position].isSubscribe
            holder.itemCheck.setOnClickListener {
                data[position].isSubscribe = !data[position].isSubscribe!!
                notifyDataSetChanged()
                getDataCallBack.getDataChange(data)
            }
            holder.parent.visibility = if(it.id==0L)
                GONE
            else
                VISIBLE
        }

        //塞資料
        fun setData(data: List<ProviderBean>) {
            this.data = data
            notifyDataSetChanged()
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var parent = itemView
            var itemCheck: CheckBox = itemView.findViewById(R.id.cb)
        }
    }