package com.trenddc.hashksa.task.domain.auth.repository
import com.trenddc.hashksa.task.domain.auth.entity.UserModel
import com.trenddc.hashksa.task.domain.auth.request.LogInRequest
import com.trenddc.hashksa.task.domain.utils.Resource

interface AuthRemoteRepository {
  suspend fun login(request: LogInRequest): Resource<UserModel>
}