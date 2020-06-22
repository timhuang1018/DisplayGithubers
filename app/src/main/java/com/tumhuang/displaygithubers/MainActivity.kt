package com.tumhuang.displaygithubers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tumhuang.displaygithubers.model.RemoteApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val result = RemoteApi.instance.getUsers()
            Log.d("MainActivity","result: ${result.size}")
        }
    }
}