package app.fawry.task.data.home

import app.fawry.task.domain.home.model.HomeResponse
import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
  private val remoteDataSource: HomeRemoteDataSource
) : HomeRepository {
  override suspend fun news(): Resource<BaseResponse<ArrayList<NewsModel>>> = remoteDataSource.news()
  override suspend fun home(): Resource<BaseResponse<HomeResponse>> = remoteDataSource.home()
  override suspend fun notifications(): Resource<BaseResponse<ArrayList<NotificationItem>>> = remoteDataSource.notifications()
}