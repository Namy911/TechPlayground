package com.wpay.core.data.repository

import com.wpay.core.data.database.dao.UserDao
import com.wpay.core.domain.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userDao: UserDao) : UserRepository {
    override fun getUserProfile(login: String, password: String) = flow {
        val user = userDao.getUserProfile(email = login, password = password)
        user?.let {
            emit(it)
        } ?: run {
            throw Exception("User not found")
        }
    }
}