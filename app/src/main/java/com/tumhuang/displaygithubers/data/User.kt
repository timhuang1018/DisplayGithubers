package com.tumhuang.displaygithubers.data

import com.squareup.moshi.Json
import com.tumhuang.displaygithubers.adapter.AdapterClick
import com.tumhuang.displaygithubers.adapter.RecyclerItem

data class User (
    @field:Json(name = "id") override val id:Int,
    @field:Json(name = "avatar_url") val headPic :String,
    @field:Json(name = "login") val login:String,
    @field:Json(name="site_admin") val isAdmin :Boolean,
    @field:Json(name = "name") val name : String
): RecyclerItem,AdapterClick