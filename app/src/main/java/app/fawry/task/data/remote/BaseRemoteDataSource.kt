package app.fawry.task.data.remote

import android.util.Log
import app.fawry.task.core.MyApplication
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.ErrorResponse
import app.fawry.task.domain.utils.FailureStatus
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.utils.Constants
import com.structure.base_mvvm.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {
  var gson: Gson = Gson()
  private  val TAG = "BaseRemoteDataSource"

  suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    println(apiCall)
    try {
      val apiResponse = apiCall.invoke()
      if((apiResponse as BaseResponse<*>).status == Constants.SUCCESS)
        return Resource.Success(apiResponse)
      else
        return Resource.Failure(FailureStatus.API_FAIL, message = (apiResponse as BaseResponse<*>).msg)

    } catch (throwable: Throwable) {
      Log.d(TAG, "safeApiCall: "+throwable.message)
      when (throwable) {
        is HttpException -> {
          when {
            throwable.code() == 401 -> {
              return Resource.Failure(
                FailureStatus.INVALID_TOKEN,
                throwable.code(),
                MyApplication.instance.getString(R.string.please_relogin)
              )
            }
            throwable.code() == 404 -> {
//              val errorResponse = Gson().fromJson(
//                throwable.response()?.errorBody()!!.charStream().readText(),
//                ErrorResponse::class.java
//              )

              return Resource.Failure(
                FailureStatus.API_FAIL,
                throwable.code(),
                MyApplication.instance.getString(R.string.please_try_again)
              )
            }
            else -> {
              return if (throwable.response()?.errorBody()!!.charStream().readText().isEmpty()) {
                Resource.Failure(FailureStatus.API_FAIL, throwable.code())
              } else {
                try {
//                  val errorResponse = Gson().fromJson(
//                    throwable.response()?.errorBody()!!.charStream().readText(),
//                    ErrorResponse::class.java
//                  )
//                  Resource.Failure(FailureStatus.API_FAIL, throwable.code(), errorResponse?.detail)
                  return Resource.Failure(
                    FailureStatus.API_FAIL,
                    throwable.code(),
                    MyApplication.instance.getString(R.string.please_try_again)
                  )
                } catch (ex: JsonSyntaxException) {
                  return Resource.Failure(
                    FailureStatus.API_FAIL,
                    throwable.code(),
                    MyApplication.instance.getString(R.string.please_try_again)
                  )
                }
              }
            }
          }
        }

        is UnknownHostException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        is ConnectException -> {
          return Resource.Failure(FailureStatus.NO_INTERNET)
        }

        else -> {
          return Resource.Failure(FailureStatus.OTHER)
        }
      }
    }
  }
}