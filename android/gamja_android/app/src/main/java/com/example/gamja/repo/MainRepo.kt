package com.example.gamja.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gamja.model.Diary
import com.example.gamja.utils.UserApi

class MainRepo(application:Application) {
    val context=application
    //서버에서 다이어리 갖고오기
    fun getMyDiary() :List<Diary> {
        val diaryList= arrayListOf<Diary>()
        diaryList.add(Diary(diaryId = "1",title = "헬스"))
        diaryList.add(Diary(diaryId = "2",title = "미용"))
        diaryList.add(Diary(diaryId = "3",title = "요리"))
        diaryList.add(Diary(diaryId = "4",title = "자취"))
        diaryList.add(Diary(diaryId = "5",title = "바낸"))
        diaryList.add(Diary(diaryId = "6",title = "청산"))
        diaryList.add(Diary(diaryId = "7",title = "100달라"))
        diaryList.add(Diary(diaryId = "8",title = "떡상"))
        diaryList.add(Diary(diaryId = "9",title = "빅숏"))
        diaryList.add(Diary(diaryId = "10",title = "불장"))
        return diaryList
    }
    //서버에 다이어리 추가하기
    fun addMyDiary(){

    }
    fun getUserName():String{
        return UserApi().loadUserNameData(context)
    }
}