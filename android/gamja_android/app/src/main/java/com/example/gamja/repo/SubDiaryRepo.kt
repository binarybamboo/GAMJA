package com.example.gamja.repo

import android.app.Application
import com.example.gamja.model.SubDiary
import com.example.gamja.utils.UserApi

class SubDiaryRepo(application: Application) {
    val context=application
    //서버에서 다이어리 갖고오기
    fun getSubDiary() :List<SubDiary> {
        val diaryList= arrayListOf<SubDiary>()
        diaryList.add(SubDiary(subDiaryId = "1",title = "헬스",time = "12345667",img = "",content = "감자1"))
        diaryList.add(SubDiary(subDiaryId = "2",title = "헬스",time = "12345662",img = "",content = "감자2"))
        diaryList.add(SubDiary(subDiaryId = "3",title = "헬스",time = "12345663",img = "",content = "감자3"))
        diaryList.add(SubDiary(subDiaryId = "4",title = "헬스",time = "12345664",img = "",content = "감자4"))
        diaryList.add(SubDiary(subDiaryId = "5",title = "헬스",time = "12345665",img = "",content = "감자5"))
        diaryList.add(SubDiary(subDiaryId = "6",title = "헬스",time = "12345666",img = "",content = "감자5"))
        diaryList.add(SubDiary(subDiaryId = "7",title = "헬스",time = "12345668",img = "",content = "감자7"))
        diaryList.add(SubDiary(subDiaryId = "8",title = "헬스",time = "12345669",img = "",content = "감자8"))
        diaryList.add(SubDiary(subDiaryId = "9",title = "헬스",time = "12345623",img = "",content = "감자9"))
        diaryList.add(SubDiary(subDiaryId = "10",title = "헬스",time = "123456324",img = "",content = "감자10"))
        return diaryList
    }
    //서버에 다이어리 추가하기
    fun addSubDiary(){

    }
}