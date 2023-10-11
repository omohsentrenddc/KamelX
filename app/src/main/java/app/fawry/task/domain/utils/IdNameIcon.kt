package app.fawry.task.domain.home.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import java.io.Serializable

@Keep
data class IdNameIcon(
  @SerializedName("icon")
  @Expose
  var icon: String = "",
  @SerializedName("id")
  @Expose
  var id: Int = 0,
  @SerializedName("name")
  @Expose
  var name: String = "",
  var selected: Boolean = false,
) : Serializable