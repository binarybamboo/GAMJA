package com.example.gamja.model

import com.google.gson.annotations.SerializedName

data class PostDiary(
    @SerializedName("name")
    var name:String?=null,
    @SerializedName("description")
    var description:String?=null
)
