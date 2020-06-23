package com.tumhuang.displaygithubers.config

import androidx.lifecycle.MutableLiveData
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.helper.EventWrapper

sealed class RequestState {
    data class Success(val user:MutableLiveData<List<User>>):RequestState()
    object Failure:RequestState()
}

data class RequestUsers(val user:MutableLiveData<List<User>>,
                        val isLoading:MutableLiveData<Boolean>,
                        val error :MutableLiveData<EventWrapper<String>>
)