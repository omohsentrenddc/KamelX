package app.fawry.task.domain.auth.use_case

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.repository.AuthRepository
import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.*
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AuthUseCase @Inject constructor(
  private val repository: AuthRepository,
  private val userLocalUseCase: UserLocalUseCase
) {
  private  val TAG = "AuthUseCase"

  fun login( request: LogInRequest): Flow<Resource<BaseResponse<LoginResponse>>> = flow {

    emit(Resource.Loading) //show loader
    val result = repository.login(request)
    if (result is Resource.Success) {
      if(result.value.data.data.isActive == 1){
        result.value.data.data.token = result.value.data.token.toString()
//        result.value.data.data.token = result.value.data.token?.accessToken.toString()
        userLocalUseCase.invoke(result.value.data)
      }
    }
    emit(result)//send result for collecting
  }.flowOn(Dispatchers.IO)


  fun register(
    request: RegisterRequest
  ): Flow<Resource<BaseResponse<LoginResponse>>> = flow {

    emit(Resource.Loading)
    val result = repository.register(request)
    if (result is Resource.Success) {
      if(result.value.data.data.isActive == 1) {
        result.value.data.data.token = result.value.data.token.toString()
//        result.value.data.data.token = result.value.data.token?.accessToken.toString()
        userLocalUseCase.invoke(result.value.data)
      }
    }
    emit(result)
  }.flowOn(Dispatchers.IO)

  fun confirmCode(
    request: ForgetPasswordRequest
  ): Flow<Resource<BaseResponse<*>>> = flow {
    emit(Resource.Loading)
    val result = repository.confirmCode(request)
    emit(result)
  }.flowOn(Dispatchers.IO)

  fun forgetPassword(
    request: ForgetPasswordRequest
  ): Flow<Resource<BaseResponse<ForgetPasswordResponse>>> = flow {
    emit(Resource.Loading)
    val result = repository.forgetPassword(request)
    emit(result)
  }.flowOn(Dispatchers.IO)

  fun updatePassword(
    request: UpdatePasswordRequest
  ): Flow<Resource<BaseResponse<*>>> = flow {
    emit(Resource.Loading)
    val result = repository.updatePassword(request)
    emit(result)
  }.flowOn(Dispatchers.IO)

//
//  fun registerSecond(
//    request: RegisterRequest
//  ): Flow<Resource<BaseResponse<LoginResponse>>> = flow {
//
//    emit(Resource.Loading)
//    val result = repository.registerSecond(request)
//    if (result is Resource.Success) {
//      Log.d(TAG, "registerSecond: user")
//      Log.d(TAG, "registerSecond: ${userLocalUseCase.getKeyFromLocal(Constants.TOKEN)}")
//      result.value.data.data.jwtToken = userLocalUseCase.getKeyFromLocal(Constants.TOKEN)
//      Log.d(TAG, "registerSecond_jwtToken: ${result.value.data.data.jwtToken}")
//      userLocalUseCase.invoke(result.value.data.data)
//      Log.d(TAG, "registerSecond_id: ${userLocalUseCase.invoke().id}")
////      Log.d(TAG, "registerSecond_id_2: ${userLocalUseCase.getKeyFromLocal("id")}")
//    }
//    emit(result)
//  }.flowOn(Dispatchers.IO)
//
//
//  fun forgetPassword(
//    request: ForgetPasswordRequest
//  ): Flow<Resource<BaseResponse<ForgetPasswordResponse>>> = flow {
//    emit(Resource.Loading)
//    val result = repository.forgetPassword(request)
//    emit(result)
//  }.flowOn(Dispatchers.IO)
//
//  fun confirmCode(
//    request: ForgetPasswordRequest
//  ): Flow<Resource<BaseResponse<*>>> = flow {
//    emit(Resource.Loading)
//    val result = repository.confirmCode(request)
//    emit(result)
//  }.flowOn(Dispatchers.IO)
//
//  fun updatePassword(
//    request: UpdatePasswordRequest
//  ): Flow<Resource<BaseResponse<*>>> = flow {
//    emit(Resource.Loading)
//    val result = repository.updatePassword(request)
//    emit(result)
//  }.flowOn(Dispatchers.IO)
//
//  fun updateToken(
//    request: UpdateTokenRequest
//  ): Flow<Resource<BaseResponse<*>>> = flow {
////    emit(Resource.Loading)
//    val result = repository.updateToken(request)
//    emit(result)
//  }.flowOn(Dispatchers.IO)
//
//  fun updateToken(
//    request: UpdateTokenGuestUserRequest
//  ): Flow<Resource<BaseResponse<LoginResponse>>> = flow {
////    emit(Resource.Loading)
//    val result = repository.updateTokenGuestUser(request)
//    emit(result)
//    if(result is Resource.Success){
//      val user = userLocalUseCase.invoke()
//      user.turnNotification = result.value.data.data.turnNotification
//      userLocalUseCase.invoke(user)
//    }
//  }.flowOn(Dispatchers.IO)
//
////
//  fun updateProfile(
//    request: UpdateProfileRequest
//  ): Flow<Resource<BaseResponse<LoginResponse>>> = flow {
//
//    emit(Resource.Loading)
//    val result = repository.updateProfile(request)
//    if (result is Resource.Success) {
//      result.value.data.data.jwtToken = userLocalUseCase.getKeyFromLocal(Constants.TOKEN)
//      userLocalUseCase.invoke(result.value.data.data)
//    }
//    emit(result)
//  }.flowOn(Dispatchers.IO)
  
}