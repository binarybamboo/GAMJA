package com.example.gamja.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gamja.R
import com.example.gamja.databinding.ActivityLoginBinding
import com.example.gamja.databinding.ActivityMainBinding
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var userName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.logoutBtn.setOnClickListener {
            logOut()
            val sharedPref = getSharedPreferences(
                "pref", Context.MODE_PRIVATE)
            val edit= sharedPref?.edit()
            edit?.clear()
            edit?.commit()
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
}