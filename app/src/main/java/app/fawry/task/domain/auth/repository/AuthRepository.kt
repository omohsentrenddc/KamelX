package com.trenddc.hashksa.task.domain.auth.repository

import com.trenddc.hashksa.task.domain.auth.entity.LoginResponse
import com.trenddc.hashksa.task.domain.auth.entity.UserModel
import com.trenddc.hashksa.task.domain.auth.request.*
import com.trenddc.hashksa.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import com.trenddc.hashksa.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import com.trenddc.hashksa.task.domain.auth.request.forgetPassword.UpdateTokenGuestUserRequest
import com.trenddc.hashksa.task.domain.auth.request.social.LogInSocialRequest
import com.trenddc.hashksa.task.domain.utils.BaseResponse
import com.trenddc.hashksa.task.domain.utils.Resource

interface AuthRepository {

  suspend fun login(request: LogInRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun loginSocial(request: LogInSocialRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun register(request: RegisterRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun registerSecond(request: RegisterRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<ForgetPasswordResponse>>
  suspend fun confirmCode(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
  suspend fun updatePassword(request: UpdatePasswordRequest): Resource<BaseResponse<*>>
  suspend fun updateToken(request: UpdateTokenRequest): Resource<BaseResponse<*>>
  suspend fun updateTokenGuestUser(request:UpdateTokenGuestUserRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun updateProfile(request: UpdateProfileRequest): Resource<BaseResponse<LoginResponse>>

}