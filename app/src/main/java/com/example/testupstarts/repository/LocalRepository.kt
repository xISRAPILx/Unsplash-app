package com.example.testupstarts.repository

interface LocalRepository {
    fun saveToken(token:String)
    fun removeToken(token:String)
    fun isGuest():Boolean
    fun getToken(): String?
}