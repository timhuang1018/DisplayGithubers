package com.tumhuang.displaygithubers.config

import androidx.lifecycle.MutableLiveData
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.helper.EventWrapper

/**
 * let UI bind each to display data or info
 */
data class RequestState<T>(val data:MutableLiveData<T>,
                           val isLoading:MutableLiveData<Boolean>,
                           val error :MutableLiveData<EventWrapper<String>>
)