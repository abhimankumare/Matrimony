package com.example.matrimony.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimony.R


internal class ChatAdapter(val context: Context, val data: ArrayList<String>) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_text2: TextView = view.findViewById(R.id.tv_text2)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_text_row_chat_screen, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data[position]
        holder.tv_text2.text = data.toString()

    }
    override fun getItemCount(): Int {
        return data.size
    }
}
