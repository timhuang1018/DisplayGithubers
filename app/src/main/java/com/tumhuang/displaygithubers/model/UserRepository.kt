package com.tumhuang.displaygithubers.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tumhuang.displaygithubers.config.RequestState
import com.tumhuang.displaygithubers.config.RequestUsers
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.helper.EventWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRepositoryImpl(private val scope: CoroutineScope):UserRepository {
    private val remoteApi = RemoteApi.instance
    private val user = MutableLiveData<List<User>>()
    private val isLoading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<EventWrapper<String>>()
    private var page = 1
    private val perPage = 20

    override fun getUsers(init: Boolean): RequestUsers {
        scope.launch {
            try {
                if ((init && page>1) || page>5){
                    return@launch
                }
                isLoading.value = true
                val result = remoteApi.getUsers(page++,perPage)
                user.value = user.value?.let { list -> list + result } ?:  result
                //simple catch every error and stop action
            }catch (e:Exception){
                Log.e("getUsers",e.message)
                error.value = EventWrapper("fetch data failed")
            }finally {
                isLoading.value = false
            }
        }
        return RequestUsers(user,isLoading,error)
    }
}


interface UserRepository{
    fun getUsers(init:Boolean):RequestUsers
}