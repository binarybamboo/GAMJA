package com.example.gamja.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.gamja.model.Diary
import com.example.gamja.model.PostDiary
import com.example.gamja.repo.MainRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application):AndroidViewModel(application) {

    private val repo=MainRepo(application)
    var diarySaveList=ArrayList<Diary>()
    private var _userName=MutableLiveData<String>()
    val userName:LiveData<String>
    get() = _userName

    private var _diaryList=MutableLiveData<ArrayList<Diary>>()
    val diaryList:LiveData<ArrayList<Diary>>
        get() = _diaryList

    init {
        getUserName()
    }

    fun updateUserName(){

    }
    fun getUserName(){
        _userName.value=repo.getUserName()
    }
    //서버에서 다이어리 갖고오기
    suspend fun getMyDiary(accessToken: String){
        viewModelScope.launch {
            val response=repo.getMyDiary(accessToken)
            diarySaveList= response as ArrayList<Diary>
            _diaryList.postValue(diarySaveList)
        }
    }
    //서버에 다이어리 추가하기
    suspend fun addMyDiary(accessToken:String,postDiary: PostDiary){
        viewModelScope.launch {
            val response=repo.addMyDiary(accessToken, postDiary)
            if (response != null) {
                diarySaveList.add(response)
                _diaryList.postValue(diarySaveList)
            }

        }
    }
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}