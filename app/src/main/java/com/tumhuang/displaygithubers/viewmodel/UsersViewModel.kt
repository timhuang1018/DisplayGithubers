package com.tumhuang.displaygithubers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumhuang.displaygithubers.config.RequestState
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.helper.EventWrapper
import com.tumhuang.displaygithubers.model.UserRepositoryImpl
import com.tumhuang.displaygithubers.usecase.UserUseCase
import kotlinx.coroutines.launch

class UsersViewModel:ViewModel() {

    private val useCase :UserUseCase = UserUseCase(repository = UserRepositoryImpl())

    private val users = MutableLiveData<List<User>>()
    private val isLoading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<EventWrapper<String>>()

    fun getUsers(init:Boolean= false): RequestState<List<User>> {
        val requestState = RequestState(users,isLoading,error)
        viewModelScope.launch {
            useCase.getUsers(init,requestState)
        }
        return requestState
    }

}