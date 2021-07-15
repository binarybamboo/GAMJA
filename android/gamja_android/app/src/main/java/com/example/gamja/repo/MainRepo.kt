package com.example.gamja.repo

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gamja.model.Diary
import com.example.gamja.model.PostDiary
import com.example.gamja.retrofit.RetrofitDiaryManager
import com.example.gamja.utils.RESPONSE_STATE
import com.example.gamja.utils.UserApi
import kotlinx.coroutines.flow.Flow

class MainRepo(application:Application) {
    val context=application
    val retrofitDiaryManager=RetrofitDiaryManager.instance

    //서버에 다이어리 추가하기
    suspend fun addMyDiary(accessToken:String,postDiary:PostDiary):Diary?{
        val request=retrofitDiaryManager.addDiaryGroup(accessToken=accessToken,postDiary = postDiary)
        if (request != null) {
            if(request.isSuccessful)
                Log.d("TAG", "addMyDiary requestbody: ${request.body()}")
            return request.body()
        }
        return null
    }
    //서버에서 다이어리 갖고오기
    suspend fun getMyDiary(accessToken:String):List<Diary>?{
        val request= retrofitDiaryManager.getDiaryGroup(accessToken = accessToken)
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

    fun getUserName():String{
        return UserApi().loadUserNameData(context)
    }
}