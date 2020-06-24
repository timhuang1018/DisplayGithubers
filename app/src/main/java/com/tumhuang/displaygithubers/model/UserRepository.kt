package com.tumhuang.displaygithubers.model

import android.util.Log
import com.tumhuang.displaygithubers.config.RequestState
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.data.UserDetail
import com.tumhuang.displaygithubers.helper.EventWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRepositoryImpl(private val scope: CoroutineScope):UserRepository {
    private val remoteApi = RemoteApi.instance
    private var page = 1
    private val perPage = 20
    private var sinceId = 0

    override fun getUsers(
        init: Boolean,
        requestState: RequestState<List<User>>
    ){
        scope.launch {
            try {
                if ((init && page>1) || page>5){
                    return@launch
                }
                requestState.isLoading.value = true
                page++
                val result = remoteApi.getUsers(sinceId,perPage)
                Log.d("UserRepositoryImpl","result size:${result.size}")

                if (result.isNotEmpty()){
                    sinceId = result.last().id
                }
                requestState.data.value = requestState.data.value?.let { list -> list + result } ?: result
                //simply catch every error and toast an error message
            }catch (e:Exception){
                Log.e("getUsers",e.message)
                requestState.error.value = EventWrapper("fetch data failed")
            }finally {
                requestState.isLoading.value = false
            }
        }
    }

    override fun getUser(
        userName: String,
        requestState: RequestState<UserDetail>
    ) {
        scope.launch {
            try {
                requestState.isLoading.value = true

                requestState.data.value = remoteApi.getUser(userName)
            }catch (e:Exception){
                Log.e("getUsers",e.message)
                requestState.error.value = EventWrapper("fetch data failed")
            }finally {
                requestState.isLoading.value = false
            }
        }
    }
}


interface UserRepository{
    fun getUsers(
        init: Boolean,
        requestState: RequestState<List<User>>
    )
    fun getUser(
        userName: String,
        requestState: RequestState<UserDetail>
    )
}