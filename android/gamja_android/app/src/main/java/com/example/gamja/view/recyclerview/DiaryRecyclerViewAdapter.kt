package com.example.gamja.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamja.R
import com.example.gamja.model.Diary

class DiaryRecyclerViewAdapter (private val context: Context): RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder>() {
    var datas = mutableListOf<Diary>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_diary_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val diaryTitle: TextView = itemView.findViewById(R.id.diary_item_title)
        private val diaryImg: ImageView = itemView.findViewById(R.id.diary_img)
        fun bind(item: Diary) {
            diaryTitle.text = item.title
            diaryImg.setOnClickListener {
            }
        }
    }
}