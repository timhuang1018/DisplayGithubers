package com.tumhuang.displaygithubers.data

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "id")val id:Int,
    @field:Json(name = "avatar_url") val headPic :String,
    @field:Json(name = "login") val login:String,
    @field:Json(name="site_admin") val isAdmin :Boolean,
    @field:Json(name = "name") val name : String
)