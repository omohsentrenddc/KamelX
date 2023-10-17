package app.fawry.task.domain.home.model.match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class FirstCompetitor(
    @SerializedName("camel_image")
    @Expose
    var camelImage: String = "",
    @SerializedName("camel_name")
    @Expose
    var camelName: String = "",
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("owner_image")
    @Expose
    var ownerImage: String = "",
    @SerializedName("owner_name")
    @Expose
    var ownerName: String = ""
)