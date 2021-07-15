package com.example.gamja.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gamja.R
import com.example.gamja.databinding.ActivityAddSubDiaryBinding
import com.example.gamja.model.PostSubDiary
import com.example.gamja.viewmodels.SubDiaryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddSubDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSubDiaryBinding
    private lateinit var accessToken:String
    private lateinit var groupId:String
    var PICK_IMAGE_FROM_ALBUM = 0
    var photoUri: Uri? = null
    private val subDiaryViewModel: SubDiaryViewModel by lazy {
        ViewModelProvider(this, SubDiaryViewModel.Factory(application)).get(SubDiaryViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddSubDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        accessToken=intent.getStringExtra("accessToken")!!
        groupId=intent.getStringExtra("diaryGroupId")!!
        binding.subDiaryImg.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
            Log.d("TAG", "onClick: upload room img")
        }
        binding.postSubDiaryBtn.setOnClickListener {
            postSubDiary()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_FROM_ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                //This is path to the selected image
                photoUri = data?.data
                Log.d("TAG", "photouri ${photoUri}")
                binding.subDiaryImg.setImageURI(photoUri)

            } else {
                //Exit the addphotoactivity if you leave the album without selecting it
                Toast.makeText(this, "사진등록을 취소하셨습니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }
    }
    fun postSubDiary(){
        val postSubDiary=PostSubDiary()
        postSubDiary.description=binding.subDiaryContent.text.toString()
        postSubDiary.img=photoUri
        postSubDiary.name=binding.subDiaryTitle.text.toString()
        postSubDiary.diaryGroupId=groupId
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                subDiaryViewModel.addSubDiary(accessToken, postSubDiary)
            }
            finish()
        }

    }
}