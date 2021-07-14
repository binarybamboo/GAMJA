package com.example.gamja.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gamja.R
import com.example.gamja.databinding.ActivityDetailDiaryBinding

class DetailDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDiaryBinding
    lateinit var diaryTitle:String
    lateinit var diaryContent:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_detail_diary)
        binding.lifecycleOwner=this
        binding.activity = this@DetailDiaryActivity
        val view = binding.root
        setContentView(view)

        diaryTitle=intent.getStringExtra("subDiaryTitle")!!
        diaryContent=intent.getStringExtra("subDiaryContent")!!
    }
}