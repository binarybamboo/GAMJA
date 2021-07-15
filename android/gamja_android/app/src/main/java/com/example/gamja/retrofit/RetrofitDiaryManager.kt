package com.example.gamja.retrofit

import android.util.Log
import com.example.gamja.model.Diary
import com.example.gamja.model.PostDiary
import com.example.gamja.model.PostSubDiary
import com.example.gamja.model.SubDiary
import com.example.gamja.utils.MyApi
import com.example.gamja.utils.RESPONSE_STATE
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse


class RetrofitDiaryManager {
    companion object {
        val instance = RetrofitDiaryManager()
    }

    //레트로핏 인터페이스 가져오기
    private val retrofitService: RetrofitService? =
        RetrofitClient.getClient(MyApi.BASE_URL)?.create(
            RetrofitService::class.java
        )

    //다이어리 추가하기
    suspend fun addDiaryGroup(
        accessToken: String, postDiary: PostDiary
    ): Response<Diary>? {
        val params = HashMap<String, Any>()
        params["name"] = postDiary.name!!
        params["description"] = postDiary.description!!
        Log.d("TAG", "postDiary to string $postDiary")
        return retrofitService?.addDiaryGroup("Bearer $accessToken",params)
    }
    //다이어리 갖고오기
    suspend fun getDiaryGroup(accessToken: String) : Response<List<Diary>>? {
        return retrofitService?.getDiaryGroup("Bearer $accessToken")
    }
    //서브다이어리 추가하기
    suspend fun addSubDiary(
        accessToken: String, postSubDiary: PostSubDiary): Response<SubDiary>? {
        val params = HashMap<String, Any>()
//        params["name"] = postSubDiary.name!!
//        params["description"] = postSubDiary.description!!
        Log.d("TAG", "postSubDiary to string $postSubDiary")
        return retrofitService?.addSubDiary("Bearer $accessToken",params)
    }
    //서브다이어리 갖고오기
//    suspend fun getSubDiary(accessToken: String) : Response<List<SubDiary>>? {
//        return retrofitService?.getSubDiary("Bearer $accessToken")
//    }
}