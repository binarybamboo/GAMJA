package com.example.gamja.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamja.R
import com.example.gamja.model.SubDiary

class SubDiaryRecyclerViewAdapter (private val context: Context): RecyclerView.Adapter<SubDiaryRecyclerViewAdapter.ViewHolder>() {
    var datas = mutableListOf<SubDiary>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sub_diary_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.diaryImg.setOnClickListener {
            itemClickListener.onClick(it,position,datas[position].title!!,datas[position].content!!)
        }
    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int,subDiaryTitle:String,subDiaryContent:String)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val diaryImg: ImageView = itemView.findViewById(R.id.sub_diary_img)
        fun bind(item: SubDiary) {
        }
    }
}