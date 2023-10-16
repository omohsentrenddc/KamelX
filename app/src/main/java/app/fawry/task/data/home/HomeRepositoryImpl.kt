package app.fawry.task.data.home

import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
  private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
  override suspend fun news(): Resource<BaseResponse<List<NewsModel>>> = remoteDataSource.news()
}