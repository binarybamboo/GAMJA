package com.example.gamja.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.gamja.model.Diary
import com.example.gamja.model.SubDiary
import com.example.gamja.repo.SubDiaryRepo

class SubDiaryViewModel(application: Application): AndroidViewModel(application) {

    private val repo= SubDiaryRepo(application)

    private var _userName= MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private var _diaryList= MutableLiveData<List<SubDiary>>()
    val diaryList: LiveData<List<SubDiary>>
        get() = _diaryList

    init {
        getSubDiary()
    }

    fun updateUserName(){

    }
    //서버에서 다이어리 갖고오기
    private fun getSubDiary(){
        _diaryList.value= repo.getSubDiary()
    }
    //서버에 다이어리 추가하기
    fun addMyDiary(){
       // repo.addMyDiary()
    }
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SubDiaryViewModel(application) as T
        }
    }
}