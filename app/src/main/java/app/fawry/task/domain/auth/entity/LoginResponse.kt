package com.trenddc.hashksa.task.domain.auth.entity

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
  @SerializedName("token")
  @Expose val token: AccessToken = AccessToken(),
  @SerializedName("data")
  @Expose val data: UserModel = UserModel(),
)

data class AccessToken(
  @SerializedName("accessToken")
  @Expose val accessToken: String = "",
)