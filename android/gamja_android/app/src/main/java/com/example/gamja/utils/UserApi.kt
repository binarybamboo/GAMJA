package com.example.gamja.utils

import android.content.Context

class UserApi {

    fun loadUserNameData(context: Context): String {
        val sharedPref = context.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
        return sharedPref?.getString("userName", "")!!
    }
    fun saveToken(context: Context,refreshToken:String,accessToken:String){
        val sharedPref = context.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
        val edit= sharedPref?.edit()
            edit?.putString("refreshToken", refreshToken)
            edit?.putString("accessToken", accessToken)
            edit?.apply()
    }
    fun loadAccessToken(context: Context):String{
        val sharedPref = context.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )

        return sharedPref?.getString("accessToken", "")!!
    }
    fun loadRefreshToken(context: Context):String{
        val sharedPref = context.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
        return sharedPref?.getString("refreshToken", "")!!
    }
}