package com.example.gamja.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gamja.databinding.ActivityLoginBinding
import com.kakao.sdk.user.UserApiClient


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener {
            login()
        }
    }
    fun login(){
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e("TAG", "로그인 실패", error)
            }
            else if (token != null) {
                Log.i("TAG", "로그인 성공 ${token.accessToken}")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}