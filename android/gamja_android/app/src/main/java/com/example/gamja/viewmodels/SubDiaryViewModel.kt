package com.example.gamja.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.gamja.model.Diary
import com.example.gamja.model.PostDiary
import com.example.gamja.model.SubDiary
import com.example.gamja.repo.MainRepo
import com.example.gamja.repo.SubDiaryRepo
import kotlinx.coroutines.launch

class SubDiaryViewModel(application: Application): AndroidViewModel(application) {

    private val repo= SubDiaryRepo(application)
    var diarySaveList=ArrayList<SubDiary>()

    private var _diaryList=MutableLiveData<ArrayList<SubDiary>>()
    val diaryList:LiveData<ArrayList<SubDiary>>
        get() = _diaryList

    init {
    }

    suspend fun getSubDiary(accessToken: String){
        viewModelScope.launch {
            val response=repo.getSubDiary(accessToken)
            _diaryList.postValue(diarySaveList)
        }
    }
    suspend fun addSubDiary(accessToken:String,postDiary: PostDiary){
        viewModelScope.launch {
            val response=repo.addSubDiary(accessToken, postDiary)
            if (response != null) {
                _diaryList.postValue(diarySaveList)
            }

        }
    }
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SubDiaryViewModel(application) as T
        }
    }
}