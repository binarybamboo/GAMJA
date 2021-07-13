package com.example.gamja.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gamja.utils.UserApi
import com.example.gamja.databinding.ActivityLoginBinding
import com.kakao.sdk.user.UserApiClient


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var userName:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadUserNameData()

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
                goNextActivity(userName)
            }
        }
    }
    fun loadUserNameData() {
        userName= UserApi().loadUserNameData(this)
    }

    private fun goNextActivity(name:String){
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("TAG", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i("TAG", "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
            }
        }
        //이름이 없다면 이름설정창으로
        if(name==""){
            startActivity(Intent(this, UserNameActivity::class.java))
            finish()
        }
        //아니라면 메인으로 넘어가기
        else{

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}