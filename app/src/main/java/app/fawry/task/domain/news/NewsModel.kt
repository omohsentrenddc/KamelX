package app.fawry.task.domain.news


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class NewsModel(
    @SerializedName("date")
    @Expose
    var date: String = "",
    @SerializedName("description")
    @Expose
    var description: String = "",
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("image")
    @Expose
    var image: String = "",
    @SerializedName("title")
    @Expose
    var title: String = ""
)