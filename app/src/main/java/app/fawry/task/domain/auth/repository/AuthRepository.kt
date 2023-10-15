package app.fawry.task.domain.auth.repository

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource

interface AuthRepository {

  suspend fun login(request: LogInRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun register(request: RegisterRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun updatePassword(request: UpdatePasswordRequest): Resource<BaseResponse<*>>
  suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<ForgetPasswordResponse>>
  suspend fun confirmCode(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
}