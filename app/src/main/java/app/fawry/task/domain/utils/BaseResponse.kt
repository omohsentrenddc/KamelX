package app.fawry.task.domain.utils

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
  val data: T,
  @SerializedName("status")
  val status: String,
  @SerializedName("msg")
  val msg: String,
  @SerializedName("status_code")
  val code: Int,
)