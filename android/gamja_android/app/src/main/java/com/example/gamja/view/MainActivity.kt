package com.example.gamja.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gamja.R
import com.example.gamja.databinding.ActivityMainBinding
import com.example.gamja.view.dialog.AddDiaryDialog
import com.example.gamja.viewmodels.MainViewModel
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var userName:String
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.userName.observe(this,{

        })

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.vm=mainViewModel
        binding.activity = this@MainActivity
        val view = binding.root
        setContentView(view)

        binding.logoutBtn.setOnClickListener {
            logOut()
            val sharedPref = getSharedPreferences(
                "pref", Context.MODE_PRIVATE)
            val edit= sharedPref?.edit()
            edit?.clear()
            edit?.apply()
        }
    }
    fun logOut(){
        // 로그아웃

        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e("TAG", "연결 끊기 실패", error)
            }
            else {
                Log.i("TAG", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }
    fun goAddDiaryDialog(){
        Log.d("TAG", "main goAddDiaryDialog: ")
        val dialog=AddDiaryDialog(this)
        dialog.myDig()

        dialog.setOnClickListener(object : AddDiaryDialog.OnClickListener{
            override fun onClicked() {
                //다이어리추가
                Log.d("TAG", "click dialog ")
            }

        })
    }
}