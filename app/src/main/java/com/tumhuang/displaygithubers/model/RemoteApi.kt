package com.tumhuang.displaygithubers.model

import com.tumhuang.displaygithubers.data.User
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    companion object{
        private val baseUrl = "https://api.github.com"

        val instance : RemoteApi by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(RemoteApi::class.java)
        }
    }

    @GET("/users")
    suspend fun getUsers(@Query("page")page:Int=1,@Query("per_page")perPage:Int=20):List<User>
}

