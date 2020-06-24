package com.tumhuang.displaygithubers.model

import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.data.UserDetail
import com.tumhuang.displaygithubers.helper.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApi {

    companion object{
        private val baseUrl = "https://api.github.com"

        val client = OkHttpClient
            .Builder()
            .addInterceptor(LoggingInterceptor())
            .build()

        val instance : RemoteApi by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(RemoteApi::class.java)
        }
    }

    @GET("/users")
    suspend fun getUsers(@Query("since")sinceId:Int,@Query("per_page")perPage:Int):List<User>

    @GET("/users/{userName}")
    suspend fun getUser(@Path("userName")userName:String): UserDetail

}

