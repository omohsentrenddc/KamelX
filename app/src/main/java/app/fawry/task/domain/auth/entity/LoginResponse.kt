package app.fawry.task.domain.auth.entity

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
  @SerializedName("token")
  @Expose var token: String? = "",
  @SerializedName("user")
  @Expose val data: UserModel = UserModel(),
)

data class AccessToken(
  @SerializedName("accessToken")
  @Expose val accessToken: String = "",
)