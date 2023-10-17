package app.fawry.task.data.home

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.home.model.HomeResponse
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.domain.utils.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface HomeServices {
  @GET("news")
  suspend fun news(): BaseResponse<ArrayList<NewsModel>>

  @GET("home")
  suspend fun home(): BaseResponse<HomeResponse>


  @GET("notifications")
  suspend fun notifications(): BaseResponse<ArrayList<NotificationItem>>

}