package com.example.gamja.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gamja.R
import com.example.gamja.utils.UserApi
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class SplashActivity : AppCompatActivity() {
    val SPLASH_VIEW_TIME: Long = 2000 //2초간 스플래시 화면을 보여줌 (ms)
    lateinit var userName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ //delay를 위한 handler
            HasToken()
        }, SPLASH_VIEW_TIME)
    }
    private fun HasToken(){
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //로그인 필요
                                startActivity(Intent(this, LoginActivity::class.java))
                    }
                    else {
                        //기타 에러
                    }
                }
                else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                        userName=UserApi().loadUserNameData(this)
                        if(userName!=""){
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        else{
                            startActivity(Intent(this, UserNameActivity::class.java))
                            finish()
                        }

                }
            }
        }
        else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
        }

    }
}