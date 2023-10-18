package app.fawry.task.data.home

import android.util.Log
import app.fawry.task.data.remote.BaseRemoteDataSource
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {
  suspend fun news() = safeApiCall {
    apiService.news()
  }

  suspend fun notifications() = safeApiCall {
    apiService.notifications()
  }

  suspend fun home() = safeApiCall {
    apiService.home()
  }

  suspend fun allMatches() = safeApiCall {
    apiService.allMatches()
  }

  suspend fun matchDetails(id: Int) = safeApiCall {
    apiService.matchDetails(id)
  }


}