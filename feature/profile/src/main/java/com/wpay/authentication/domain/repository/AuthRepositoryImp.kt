package com.wpay.authentication.domain.repository

import com.wpay.authentication.data.repository.AuthRepository
import com.wpay.core.domain.UserRepository
import com.wpay.core.util.DispatcherProvider
import com.wpay.core.util.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val userRepo: UserRepository
) : AuthRepository {
    override suspend fun login(username: String, password: String) = flow {
        userRepo.getUserProfile(username, password)
            .catch {
                emit(Result.Error(it.message ?: "An error occurred while fetching user data"))
            }.collect {
                emit(Result.Success(it))
            }
    }.flowOn(dispatchers.io)
}
