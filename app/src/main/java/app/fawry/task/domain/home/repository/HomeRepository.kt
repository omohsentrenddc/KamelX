package app.fawry.task.domain.home.repository

import app.fawry.task.domain.home.model.HomeResponse
import app.fawry.task.domain.home.model.match.MatchModel
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource

interface HomeRepository {

  suspend fun news(): Resource<BaseResponse<ArrayList<NewsModel>>>
  suspend fun allMatches(): Resource<BaseResponse<ArrayList<MatchModel>>>
  suspend fun matchDetails(id :Int): Resource<BaseResponse<MatchModel>>
  suspend fun home(): Resource<BaseResponse<HomeResponse>>
  suspend fun notifications(): Resource<BaseResponse<ArrayList<NotificationItem>>>

}