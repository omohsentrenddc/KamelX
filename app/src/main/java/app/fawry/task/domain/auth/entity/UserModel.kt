package app.fawry.task.domain.auth.entity

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import app.fawry.task.domain.home.entity.IdNameIcon

@Keep
data class UserModel(
//512345687
  @SerializedName("id")
  @Expose val id: Int = -1,
  @SerializedName("first_name")
  @Expose val firstName: String = "",
  @SerializedName("last_name")
  @Expose val lastName: String = "",
  @SerializedName("full_name")
  @Expose val fullName: String = "",
  @SerializedName("image")
  @Expose val image: String? = "",
  @SerializedName("phone_code")
  @Expose val phoneCode: String? = "",
  @SerializedName("code")
  @Expose val code: String? = "",
  @SerializedName("phone")
  @Expose var phone: String = "",
  @SerializedName("is_active")
  @Expose var isActive: Int = 0,
  @SerializedName("token")
  @Expose var token: String = "",
){
  //{"token":null,"user":{"id":5,"first_name":"osama","last_name":"mohsen","full_name":"osama mohsen",
  // "image":null,"phone_code":966,"phone":"0512345678","code":2483,"is_active":0}}}
}