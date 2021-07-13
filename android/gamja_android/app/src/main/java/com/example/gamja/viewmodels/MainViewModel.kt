package com.example.gamja.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gamja.repo.MainRepo

class MainViewModel(application: Application):AndroidViewModel(application) {

    private val repo=MainRepo(application)

    private var _userName=MutableLiveData<String>()
    val userName:LiveData<String>
    get() = _userName

    init {
    }

    fun updateUserName(){

    }
    //서버에서 다이어리 갖고오기
    fun getMyDiary(){
            repo.getMyDiary()
    }
    //서버에 다이어리 추가하기
    fun addMyDiary(){
            repo.addMyDiary()
    }
}