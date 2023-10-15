package app.fawry.task.data.auth

import android.util.Log
import app.fawry.task.data.remote.BaseRemoteDataSource
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val apiService: AuthServices) :
  BaseRemoteDataSource() {

  private val TAG = "AuthRemoteDataSource"
  suspend fun logIn(request: LogInRequest) = safeApiCall {
    Log.d(TAG, "logIn: worked")
    apiService.logIn(request)
  }

  suspend fun register(request: RegisterRequest) = safeApiCall {
    apiService.register(request)
  }

  suspend fun forgetPassword(request: ForgetPasswordRequest) = safeApiCall {
    apiService.forgetPassword(request)
  }

  suspend fun confirmCode(request: ForgetPasswordRequest) = safeApiCall {
    apiService.confirmCode(request)
  }

  suspend fun updatePassword(request: UpdatePasswordRequest) = safeApiCall {
    apiService.updatePassword(request)
  }



//  suspend fun loginSocial(request: LogInSocialRequest) = safeApiCall {
//    Log.d(TAG, "logIn: worked")
//    apiService.loginSocial(request)
//  }



//  suspend fun registerSecond(request: RegisterRequest) = safeApiCall {
//    apiService.registerSecond(request)
//  }
//
//  suspend fun forgetPassword(request: ForgetPasswordRequest) = safeApiCall {
//    apiService.forgetPassword(request)
//  }
//
//  suspend fun confirmCode(request: ForgetPasswordRequest) = safeApiCall {
//    apiService.confirmCode(request)
//  }
//
//  suspend fun updatePassword(request: UpdatePasswordRequest) = safeApiCall {
//    apiService.updatePassword(request)
//  }
//
//  suspend fun updateToken(request: UpdateTokenRequest) = safeApiCall {
//    apiService.updateToken(request)
//  }
//
//  suspend fun updateTokenGuestUser(request: UpdateTokenGuestUserRequest) = safeApiCall {
//    apiService.updateTokenGuestUser(request)
//  }
//
//
//
//  /*
//    var name: String = ""
//    var email: String = ""
//    var phone: String = ""
//    var gender_id: Int = -1
//    var date_of_birth: String = ""
//    var password: String = ""
//    @Transient
//    var uri: Uri? = null
//    @Transient
//    var gender: String = ""
//   */
//
//  suspend fun updateProfile(request: UpdateProfileRequest) = safeApiCall {
//    val map = mutableMapOf(
//      "name" to request.name.toRequestBody(),
//      "email" to request.email.toRequestBody(),
//      "phone" to request.phone.toRequestBody(),
//      "gender_id" to request.gender_id.toString().toRequestBody(),
//      "date_of_birth" to request.date_of_birth.toRequestBody(),
//    )
//
//
//    when (request.uri) {
//      null -> apiService.updateProfile(request)
//      else -> {
//        apiService.updateProfile(
//          map, request.uri?.createMultipartBodyPart(MyApplication.instance, "photo")
//        )
//      }
//    }
//  }

}