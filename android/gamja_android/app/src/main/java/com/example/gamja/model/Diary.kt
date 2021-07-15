package com.example.gamja.model

import com.google.gson.annotations.SerializedName

data class Diary(
    @SerializedName("id")
        var _id:String?=null,
    @SerializedName("name")
    var name:String?=null,
    @SerializedName("createdAt")
    var createdAt:String?=null,
    @SerializedName("updatedAt")
    var updatedAt:String?=null,
    @SerializedName("diary")
    var diary:List<String>?=null
)
