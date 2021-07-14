package com.example.gamja.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.gamja.model.Diary
import com.example.gamja.repo.MainRepo

class MainViewModel(application: Application):AndroidViewModel(application) {

    private val repo=MainRepo(application)

    private var _userName=MutableLiveData<String>()
    val userName:LiveData<String>
    get() = _userName

    private var _diaryList=MutableLiveData<List<Diary>>()
    val diaryList:LiveData<List<Diary>>
        get() = _diaryList

    init {
        getMyDiary()
    }

    fun updateUserName(){

    }
    //서버에서 다이어리 갖고오기
    fun getMyDiary(){
            _diaryList.value= repo.getMyDiary()
    }
    //서버에 다이어리 추가하기
    fun addMyDiary(){
            repo.addMyDiary()
    }
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}