package com.wpay.core.data.repository

import com.wpay.core.data.database.dao.UserDao
import com.wpay.core.data.database.entity.User
import com.wpay.core.domain.repository.UserRepository
import com.wpay.core.util.Result
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

    override suspend fun insertUser(fullName: String, email: String, password: String) = try {
        val newUser = processAndInsertUser(fullName, email, password)
        Result.Success(newUser)
    } catch (e: Exception) {
        Result.Error("Error inserting user: ${e.message}")
    }

    private suspend fun processAndInsertUser(
        fullName: String,
        email: String,
        password: String,
    ): User {
        val parts = fullName.trim().split(",")
        val name = parts.firstOrNull() ?: ""
        val surname = parts.drop(1).joinToString(" ")

        if (name.isNotEmpty() && surname.isNotEmpty()) {
            val newUser = User(
                name = name,
                surname = surname,
                email = email,
                password = password
            )

            val insertedId  = userDao.insertUser(newUser)
            val insertedUser = newUser.copy(
                id = insertedId
            )

            return insertedUser
        } else {
            throw IllegalArgumentException("Invalid full name")
        }
    }
}