package app.fawry.task.domain.home.model.match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import java.io.Serializable

@Keep
data class PlayerModel(
    @SerializedName("camel_image")
    @Expose
    var camelImage: String = "",
    @SerializedName("camel_name")
    @Expose
    var camelName: String = "",
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("owner_name")
    @Expose
    var ownerName: String = "",
    @SerializedName("owner_image")
    @Expose
    var ownerImage: String = "",
    @SerializedName("color")
    @Expose
    var color: String = "",
    @SerializedName("age")
    @Expose
    var age: String = "",
    @SerializedName("origin")
    @Expose
    var origin: String = "",
    @SerializedName("winner")
    @Expose
    var winner: Boolean = false,
    @SerializedName("vote")
    @Expose
    var vote: Int = 0,
) : Serializable