package com.example.gamja.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamja.R
import com.example.gamja.databinding.ActivitySubDiaryBinding
import com.example.gamja.model.Diary
import com.example.gamja.model.SubDiary
import com.example.gamja.view.recyclerview.MainRecyclerViewAdapter
import com.example.gamja.view.recyclerview.SubDiaryRecyclerViewAdapter
import com.example.gamja.viewmodels.SubDiaryViewModel

class SubDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubDiaryBinding
    lateinit var subDiaryAdapter: SubDiaryRecyclerViewAdapter
     var diaryTitle: String?=null
    private val subDiaryViewModel: SubDiaryViewModel by lazy {
        ViewModelProvider(this, SubDiaryViewModel.Factory(application)).get(SubDiaryViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sub_diary)
        binding.lifecycleOwner=this
        binding.vm=subDiaryViewModel
        binding.activity = this@SubDiaryActivity
        val view = binding.root
        setContentView(view)
        diaryTitle= intent.getStringExtra("diaryTitle").toString()

        initRecycler()

        //데이터값이 변경되면 옵저빙으로 뷰렌더링
        subDiaryViewModel.diaryList.observe(this,{
            subDiaryAdapter.datas= it as ArrayList<SubDiary>
            subDiaryAdapter.notifyDataSetChanged()
        })

    }
    private fun initRecycler() {
        subDiaryAdapter = SubDiaryRecyclerViewAdapter(this)
        binding.subDiaryRecyclerView.adapter =subDiaryAdapter
        binding.subDiaryRecyclerView.layoutManager= GridLayoutManager(applicationContext,3)
        subDiaryAdapter.setItemClickListener(object : SubDiaryRecyclerViewAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int,subDiaryTitle:String,subDiaryContent:String) {
                //여기서 서버에서 다이어리 요청하기
                val intent = Intent(applicationContext, DetailDiaryActivity::class.java)
                intent.putExtra("subDiaryTitle",subDiaryTitle)
                intent.putExtra("subDiaryContent",subDiaryContent)
                startActivity(intent)
            }

        })
    }
    fun goToaddSubDiary(){
        startActivity(Intent(this, AddSubDiaryActivity::class.java))
    }
}