package com.example.gamja.retrofit

import com.example.gamja.model.Diary
import com.example.gamja.model.PostDiary
import com.example.gamja.model.SubDiary
import com.example.gamja.utils.MyApi
import com.example.gamja.utils.UserApi
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @POST("auth/login")
    fun sendEmail(@Body email:HashMap<String, Any>): Call<JsonElement>


    @POST("diary-group")
   suspend fun addDiaryGroup(@Header("Authorization") accessToken:String , @Body postDiary:HashMap<String, Any>): Response<Diary>

    @GET("diary-group")
    suspend fun getDiaryGroup(@Header("Authorization") accessToken:String):Response<List<Diary>>

    @POST("diary")
    suspend fun addSubDiary(@Header("Authorization") accessToken:String , @Body postSubDiary:HashMap<String, Any>): Response<SubDiary>

    @GET("diary/")
    suspend fun getSubDiary(@Header("Authorization") accessToken:String,@Query("diaryGroupId") groupId:String):Response<List<SubDiary>>
}