package com.tumhuang.displaygithubers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumhuang.displaygithubers.config.RequestState
import com.tumhuang.displaygithubers.data.UserDetail
import com.tumhuang.displaygithubers.helper.EventWrapper
import com.tumhuang.displaygithubers.model.UserRepositoryImpl
import com.tumhuang.displaygithubers.usecase.UserUseCase

class UserViewModel: ViewModel() {

    private val useCase : UserUseCase = UserUseCase(repository = UserRepositoryImpl(viewModelScope))

    private val user = MutableLiveData<UserDetail>()
    private val isLoading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<EventWrapper<String>>()

    fun getUser(userName:String): RequestState<UserDetail> {
        val requestState = RequestState(user,isLoading,error)
        useCase.getUser(userName,requestState)
        return requestState
    }

}