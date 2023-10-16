package app.fawry.task.data.auth

import app.fawry.task.domain.auth.repository.AuthRepository
import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override
  suspend fun login(request: LogInRequest) = remoteDataSource.logIn(request)
  override
  suspend fun register(request: RegisterRequest) = remoteDataSource.register(request)
  override
  suspend fun updatePassword(request: UpdatePasswordRequest) = remoteDataSource.updatePassword(request)

  override
  suspend fun forgetPassword(request: ForgetPasswordRequest) = remoteDataSource.forgetPassword(request)

  override
  suspend fun confirmCode(request: ForgetPasswordRequest) = remoteDataSource.confirmCode(request)


}