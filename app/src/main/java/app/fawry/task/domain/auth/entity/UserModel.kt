package com.trenddc.hashksa.task.domain.auth.entity

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.trenddc.hashksa.task.domain.home.entity.IdNameIcon

@Keep
data class UserModel(
  @SerializedName("id")
  @Expose val id: Int = -1,
  @SerializedName("name")
  @Expose val name: String = "",
  @SerializedName("phone")
  @Expose var phone: String = "",
  @SerializedName("email")
  @Expose var email: String = "",
  @SerializedName("photo")
  @Expose var photo: String = "",
  @SerializedName("jwt_token")
  @Expose var jwtToken: String? = "",
  @SerializedName("date_of_birth")
  @Expose var dateOfBirth: String = "",
  @SerializedName("turn_notification")
  @Expose var turnNotification: Boolean = true,
  @SerializedName("role")
  @Expose var role: String = "",
  @SerializedName("gender_id")
  @Expose var genderId: IdNameIcon = IdNameIcon(),
)