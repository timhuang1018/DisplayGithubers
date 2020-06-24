package com.tumhuang.displaygithubers.usecase

import com.tumhuang.displaygithubers.config.RequestState
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.data.UserDetail
import com.tumhuang.displaygithubers.model.UserRepository

/**
 * Do business logic here, and operate repository
 * @see UserRepository
 */
class UserUseCase(private val repository: UserRepository) {

    fun getUsers(
        init: Boolean,
        requestState: RequestState<List<User>>
    ) {
        repository.getUsers(init,requestState)
    }

    fun getUser(
        userName: String,
        requestState: RequestState<UserDetail>
    ) {
        repository.getUser(userName,requestState)
    }
}