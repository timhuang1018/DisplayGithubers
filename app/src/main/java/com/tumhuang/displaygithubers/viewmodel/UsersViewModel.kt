package com.tumhuang.displaygithubers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.model.RemoteApi
import kotlinx.coroutines.launch

class UsersViewModel:ViewModel() {

    private val users = MutableLiveData<List<User>>()

    fun getUsers(): MutableLiveData<List<User>> {
        viewModelScope.launch {
            users.value = RemoteApi.instance.getUsers()
        }
        return users
    }

}