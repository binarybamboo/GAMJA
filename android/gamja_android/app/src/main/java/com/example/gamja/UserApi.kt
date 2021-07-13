package com.example.gamja

import android.content.Context

class UserApi {
    fun loadUserNameData(context: Context): String {
        val sharedPref = context?.getSharedPreferences(
            "pref", Context.MODE_PRIVATE)
       val userName= sharedPref?.getString("userName","")!!
        return userName
    }
}