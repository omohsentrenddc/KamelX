package app.fawry.task.domain.home.use_case

import app.fawry.task.domain.auth.entity.UserModel
import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.auth.request.*
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val repository: HomeRepository,
  private val userLocalUseCase: UserLocalUseCase
) {
  private  val TAG = "HomeUseCase"

  fun news(): Flow<Resource<BaseResponse<ArrayList<NewsModel>>>> = flow {
    emit(Resource.Loading) //show loader
    val result = repository.news()
    emit(result)//send result for collecting
  }.flowOn(Dispatchers.IO)
}