package com.example.gamja.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gamja.R
import com.example.gamja.databinding.ActivityLoginBinding
import com.example.gamja.databinding.ActivityUserNameBinding

class UserNameActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUserNameBinding
    private lateinit var userName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goMainBtn.setOnClickListener{
            saveUserName()
            goMain()
        }
    }
    fun saveUserName(){
        userName=binding.userNameText.text.toString()
        val sharedPref = getSharedPreferences(
            "pref", Context.MODE_PRIVATE)
        val edit= sharedPref?.edit()
        edit?.putString("userName", userName)
        edit?.apply()
        Log.d("TAG", "userName: ${userName}")
    }
    fun goMain(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}