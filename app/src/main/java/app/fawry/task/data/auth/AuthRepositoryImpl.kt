package app.fawry.task.data.auth

import app.fawry.task.domain.auth.repository.AuthRepository
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override
  suspend fun login(request: LogInRequest) = remoteDataSource.logIn(request)
  override
  suspend fun register(request: RegisterRequest) = remoteDataSource.register(request)

}