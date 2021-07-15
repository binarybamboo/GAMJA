package com.example.gamja.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gamja.R
import com.example.gamja.model.Diary

class MainRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    var datas = ArrayList<Diary>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.diary_list,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.diaryImg.setOnClickListener{
            itemClickListener.onClick(it,position,datas[position].name,datas[position]._id)
        }
    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int,title:String?,id:String?)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val diaryTitle: TextView = itemView.findViewById(R.id.diary_item_title)
        val diaryImg: ImageView = itemView.findViewById(R.id.diary_img)
        fun bind(item: Diary) {
            diaryTitle.text = item.name

        }
    }

}