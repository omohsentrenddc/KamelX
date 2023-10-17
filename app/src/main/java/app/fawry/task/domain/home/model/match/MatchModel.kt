package app.fawry.task.domain.home.model.match


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class MatchModel(
  @SerializedName("competiton")
    @Expose
    var competiton: Competiton = Competiton(),
  @SerializedName("duration")
    @Expose
    var duration: String = "",
  @SerializedName("end_date")
    @Expose
    var endDate: String = "",
  @SerializedName("first_competitor")
    @Expose
    var firstCompetitor: FirstCompetitor = FirstCompetitor(),
  @SerializedName("first_competitor_vote")
    @Expose
    var firstCompetitorVote: Int = 0,
  @SerializedName("id")
    @Expose
    var id: Int = 0,
  @SerializedName("is_available")
    @Expose
    var isAvailable: String = "",
  @SerializedName("restTime")
    @Expose
    var restTime: String = "",
  @SerializedName("round")
    @Expose
    var round: Int = 0,
  @SerializedName("second_competitor")
    @Expose
    var secondCompetitor: SecondCompetitor = SecondCompetitor(),
  @SerializedName("second_competitor_vote")
    @Expose
    var secondCompetitorVote: Int = 0,
  @SerializedName("start_date")
    @Expose
    var startDate: String = "",
  @SerializedName("winner")
    @Expose
    var winner: Any = Any()
)