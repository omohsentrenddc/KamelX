package com.trenddc.hashksa.task.domain.auth.request.forgetPassword

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class ForgetPasswordResponse(
  @SerializedName("code")
  @Expose val code: String = "",
)
