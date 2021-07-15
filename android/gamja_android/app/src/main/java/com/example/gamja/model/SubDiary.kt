package com.example.gamja.model

import com.google.gson.annotations.SerializedName

data class SubDiary(
        @SerializedName("_id")
    var subDiaryId:String?=null,
        @SerializedName("name")
    var title:String?=null,
        @SerializedName("photo")
    var img:List<String>?=null,
        @SerializedName("description")
    var content:String?=null,
        @SerializedName("state")
    var state:Double=0.0,
        @SerializedName("createdAt")
        var createdAt:String?=null,
        @SerializedName("updatedAt")
        var updatedAt:String?=null
)