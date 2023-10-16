package app.fawry.task.data.home

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface HomeServices {
  @POST("news")
  suspend fun news(): BaseResponse<List<NewsModel>>

}