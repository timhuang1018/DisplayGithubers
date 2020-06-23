package com.tumhuang.displaygithubers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumhuang.displaygithubers.config.RequestUsers
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.model.UserRepositoryImpl
import com.tumhuang.displaygithubers.usecase.UserUseCase

class UsersViewModel:ViewModel() {

    private val useCase :UserUseCase = UserUseCase(repository = UserRepositoryImpl(viewModelScope))

    fun getUsers(init:Boolean= false): RequestUsers {
        return useCase.getUsers(init)
    }

}