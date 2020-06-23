package com.tumhuang.displaygithubers.usecase

import androidx.lifecycle.MutableLiveData
import com.tumhuang.displaygithubers.config.RequestUsers
import com.tumhuang.displaygithubers.config.RequestState
import com.tumhuang.displaygithubers.helper.EventWrapper
import com.tumhuang.displaygithubers.model.UserRepository

/**
 * Do business logic here, to make it testable make repository interface
 * @see UserRepository
 */
class UserUseCase(private val repository: UserRepository) {

    fun getUsers(init:Boolean): RequestUsers {
        return repository.getUsers(init)
    }
}