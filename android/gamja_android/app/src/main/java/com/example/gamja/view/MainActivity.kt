package com.example.gamja.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamja.R
import com.example.gamja.databinding.ActivityMainBinding
import com.example.gamja.model.Diary
import com.example.gamja.view.dialog.AddDiaryDialog
import com.example.gamja.view.recyclerview.MainRecyclerViewAdapter
import com.example.gamja.viewmodels.MainViewModel
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var userName:String
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this,MainViewModel.Factory(application)).get(MainViewModel::class.java)
    }
    lateinit var mainAdapter: MainRecyclerViewAdapter
    val datas = arrayListOf<Diary>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.vm=mainViewModel
        binding.activity = this@MainActivity
        val view = binding.root
        setContentView(view)
        //리사이클러뷰 초기화
        initRecycler()


        mainViewModel.userName.observe(this,{

        })
        //데이터값이 변경되면 옵저빙으로 뷰렌더링
        mainViewModel.diaryList.observe(this,{
            mainAdapter.datas= it as ArrayList<Diary>
            mainAdapter.notifyDataSetChanged()
        })

        binding.logoutBtn.setOnClickListener {
            logOut()
            val sharedPref = getSharedPreferences(
                "pref", Context.MODE_PRIVATE)
            val edit= sharedPref?.edit()
            edit?.clear()
            edit?.apply()
        }
    }
    fun logOut(){
        // 로그아웃

        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e("TAG", "연결 끊기 실패", error)
            }
            else {
                Log.i("TAG", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }
    fun goAddDiaryDialog(){
        Log.d("TAG", "main goAddDiaryDialog: ")
        val dialog=AddDiaryDialog(this)
        dialog.myDig()

        dialog.setOnClickListener(object : AddDiaryDialog.OnClickListener{
            override fun onClicked() {
                //다이어리추가
                Log.d("TAG", "click dialog ")
            }

        })
    }
    private fun initRecycler() {
        mainAdapter = MainRecyclerViewAdapter(this)
        binding.diaryRecyclerView.adapter =mainAdapter
        binding.diaryRecyclerView.layoutManager=GridLayoutManager(applicationContext,2)
        mainAdapter.setItemClickListener(object : MainRecyclerViewAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                startActivity(Intent(applicationContext, SubDiaryActivity::class.java))
            }

        })
    }
}