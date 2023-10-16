package app.fawry.task.domain.home.repository

import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource

interface HomeRepository {

  suspend fun news(): Resource<BaseResponse<ArrayList<NewsModel>>>
}