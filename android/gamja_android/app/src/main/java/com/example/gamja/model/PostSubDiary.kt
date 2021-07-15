package com.example.gamja.model

import android.net.Uri
import java.io.File

data class PostSubDiary(
    var name:String?=null,
    var img:Uri?=null,
    var description:String?=null,
    var diaryGroupId:String?=null
)
