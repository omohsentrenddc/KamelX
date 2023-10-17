package app.fawry.task.domain.home.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import app.fawry.task.domain.home.model.match.MatchModel
import app.fawry.task.domain.news.NewsModel
import com.google.gson.annotations.Expose

@Keep
data class HomeResponse(
  @SerializedName("matches")
    @Expose
    var matches: ArrayList<MatchModel> = arrayListOf(),
  @SerializedName("news")
    @Expose
    var news: NewsModel = NewsModel()
)