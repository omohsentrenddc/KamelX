package app.fawry.task.data.auth

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.utils.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthServices {
  @POST("auth/login")
  suspend fun logIn(@Body request: LogInRequest): BaseResponse<LoginResponse>

  @POST("auth/register")
  suspend fun register(@Body request: RegisterRequest): BaseResponse<LoginResponse>

  @POST("auth/forgetPassword")
  suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<ForgetPasswordResponse>


  //SendOrCheckCodeRequest
  @POST("auth/checkCode")
  suspend fun confirmCode(@Body request: ForgetPasswordRequest): BaseResponse<*>


  @POST("auth/updatePassword")
  suspend fun updatePassword(@Body request: UpdatePasswordRequest): BaseResponse<*>

//  @POST("provider")
//  suspend fun loginSocial(@Body request: LogInSocialRequest): BaseResponse<LoginResponse>
//

//  @POST("registerStepTwo")
//  suspend fun registerSecond(@Body request: RegisterRequest): BaseResponse<LoginResponse>
//
//
//  @POST("sendCode")
//  suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<ForgetPasswordResponse>
//
//
//  //SendOrCheckCodeRequest
//  @POST("checkCode")
//  suspend fun confirmCode(@Body request: ForgetPasswordRequest): BaseResponse<*>
//
//
//  @POST("updatePassword")
//  suspend fun updatePassword(@Body request: UpdatePasswordRequest): BaseResponse<*>
//
//  @POST("updateFirebaseToken")
//  suspend fun updateToken(@Body request: UpdateTokenRequest): BaseResponse<*>
//
//  @POST("guestUserStore")//update firebase token guest
//  suspend fun updateTokenGuestUser(@Body request: UpdateTokenGuestUserRequest): BaseResponse<LoginResponse>
//
//
//
//  @POST("updateProfile")
//  suspend fun updateProfile(@Body request: UpdateProfileRequest): BaseResponse<LoginResponse>

  @Multipart
  @POST("updateProfile")
  suspend fun updateProfile(
    @PartMap mapOfOtherParams: Map<String, @JvmSuppressWildcards RequestBody>,
    @Part image: MultipartBody.Part?
  ): BaseResponse<LoginResponse>

}