package com.example.gamja.retrofit

import android.util.Log
import com.example.gamja.model.Token
import com.example.gamja.model.User
import com.example.gamja.utils.MyApi
import com.example.gamja.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitLoginManager{
    companion object{
        val instance=RetrofitLoginManager()
    }
    //레트로핏 인터페이스 가져오기
    private val retrofitService:RetrofitService?=RetrofitClient.getClient(MyApi.BASE_URL)?.create(RetrofitService::class.java)
    //이메일 보내서 리프레시 토큰 받아오기
    fun sendEmail(
        email:String?, completion:(RESPONSE_STATE, Token?)->Unit){
        var params:HashMap<String, Any> = HashMap()

        val email=email.let {
            it
        }?:""
        params["email"] = email
        val call=retrofitService?.sendEmail(email=params)
        Log.d("TAG", "email from $email")
        call?.enqueue(object : retrofit2.Callback<JsonElement> {
            //응답실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d("TAG", "onFailure: 응답이 실패했습니다 .t:$t")
                completion(RESPONSE_STATE.FAIL, null)
            }
            //응답성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d("TAG", "onResponse: 응답을 성공했습니다 response:${response.body()}")
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            val body = it.asJsonObject
                            val tokens = body.get("tokens").asJsonObject
                            val access = tokens.get("access").asJsonObject
                            val acToken = access.get("token").asString

                            val refresh = tokens.get("refresh").asJsonObject
                            val rfToken = refresh.get("token").asString

                            val tokenItem = Token(accessToken = acToken, refreshToken = rfToken)
                            Log.d("TAG", "tokenItem $tokenItem ")
                            completion(RESPONSE_STATE.OK, tokenItem)
                        }

                    }

                }

            }
        })
    }
}