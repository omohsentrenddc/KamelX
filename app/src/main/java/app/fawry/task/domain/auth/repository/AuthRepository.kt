package app.fawry.task.domain.auth.repository

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.UpdateProfileRequest
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.domain.auth.request.*
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.auth.request.forgetPassword.UpdateTokenGuestUserRequest
import app.fawry.task.domain.auth.request.social.LogInSocialRequest

interface AuthRepository {

  suspend fun login(request: LogInRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun loginSocial(request: LogInSocialRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun register(request: RegisterRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun registerSecond(request: RegisterRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<ForgetPasswordResponse>>
  suspend fun confirmCode(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
  suspend fun updatePassword(request: UpdatePasswordRequest): Resource<BaseResponse<*>>
  suspend fun updateToken(request: UpdateTokenRequest): Resource<BaseResponse<*>>
  suspend fun updateTokenGuestUser(request: UpdateTokenGuestUserRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun updateProfile(request: UpdateProfileRequest): Resource<BaseResponse<LoginResponse>>

}