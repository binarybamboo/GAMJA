package com.example.gamja.utils

import android.app.Application

//애플리케이션 인스턴스 반환하기위해
class App : Application(){
    companion object{
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance =this
    }
}