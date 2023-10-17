package app.fawry.task.domain.home.model.match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class Competiton(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("name")
    @Expose
    var name: String = ""
)