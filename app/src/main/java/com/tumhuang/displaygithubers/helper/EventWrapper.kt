package com.tumhuang.displaygithubers.helper

class EventWrapper<T>(private val content:T) {
    private var isUsed = false
    fun contentGetHandled():T?{
        if (isUsed){
            return null
        }else{
            isUsed = true
            return content
        }
    }
}