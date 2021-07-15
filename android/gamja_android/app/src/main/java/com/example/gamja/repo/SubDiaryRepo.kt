package com.example.gamja.repo

import android.app.Application
import android.util.Log
import com.example.gamja.model.Diary
import com.example.gamja.model.PostDiary
import com.example.gamja.model.PostSubDiary
import com.example.gamja.model.SubDiary
import com.example.gamja.retrofit.RetrofitDiaryManager
import com.example.gamja.utils.UserApi

class SubDiaryRepo(application: Application) {
    val context=application
    val retrofitDiaryManager= RetrofitDiaryManager.instance
    //서버에 서브다이어리 추가하기
    suspend fun addSubDiary(accessToken:String,postSubDiary: PostSubDiary): SubDiary?{
        val request=retrofitDiaryManager.addSubDiary(accessToken=accessToken,postSubDiary = postSubDiary)
        if (request != null) {
            if(request.isSuccessful)
                Log.d("TAG", "addMyDiary requestbody: ${request.body()}")
            return request.body()
        }
        return null
    }
    //서버에서 서브다이어리 갖고오기
    suspend fun getSubDiary(accessToken:String,groupId:String):List<SubDiary>?{
        val request= retrofitDiaryManager.getSubDiary(accessToken = accessToken,groupId=groupId)
        if (request != null) {
            if(request.isSuccessful){
                Log.d("TAG", "getMyDiary success requestbody: ${request.body()}")
                return request.body()
            }
            else{
                Log.d("TAG", "getMyDiary fail requestbody: ${request.body()}")
            }
        }
        else{
            Log.d("TAG", "getMyDiary null request requestbody")}
        return null
    }
}