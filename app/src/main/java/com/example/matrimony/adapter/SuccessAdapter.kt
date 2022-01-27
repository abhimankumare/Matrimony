package com.example.matrimony.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimony.R
import com.example.matrimony.activity.RegisterActivity


internal class SuccessAdapter(val context: Context, val data: ArrayList<String>) :
    RecyclerView.Adapter<SuccessAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_self: TextView = view.findViewById(R.id.tv_self)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.success_stories_adapter, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data[position]
        holder.tv_self.text = data.toString()


    }
    override fun getItemCount(): Int {
        return data.size
    }
}