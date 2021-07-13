package com.example.gamja.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.gamja.databinding.ActivityUserNameBinding
import com.example.gamja.utils.textChangesToFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

class UserNameActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUserNameBinding
    private lateinit var userName:String
    private var flag=0
    private var myCoroutineJob:Job= Job()
    private val myCoroutineContext:CoroutineContext
    get() = Dispatchers.IO+myCoroutineJob

    @DelicateCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //키보드올리기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        binding.goMainBtn.setOnClickListener{
            //키보드내리기
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
            if(flag!=0){
                saveUserName()
                goMain()
            }
            else{
                Toast.makeText(this,"이름을 지어주세요", Toast.LENGTH_SHORT).show()
            }
        }
        GlobalScope.launch(myCoroutineContext) {
            val editFlow=binding.userNameText.textChangesToFlow()
            editFlow
                .onEach {
                Log.d("TAG", "flow를 받는다 $it")
                    //에딧텍스트길이가 한글자이상이면 확인버튼 black활성화 아니면 gray비활성화
                    if(binding.userNameText.length()!=0){
                        binding.goMainBtn.setTextColor(Color.BLACK)
                        flag=1
                    }
                    else{
                        binding.goMainBtn.setTextColor(Color.GRAY)
                        flag=0
                    }
            }.launchIn(this)
        }
    }
    override fun onDestroy() {
        myCoroutineContext.cancel()
        super.onDestroy()
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