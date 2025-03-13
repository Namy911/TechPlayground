package com.wpay.common.data.repository

import com.wpay.common.data.database.dao.UserDao
import com.wpay.common.domain.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userDao: UserDao) : UserRepository {
    override fun getUserProfile(username: String, password: String) = flow {
        val user = userDao.getUserProfile(username, password)
        user?.let {
            emit(it)
        } ?: run {
            throw Exception("User not found")
        }
    }
}