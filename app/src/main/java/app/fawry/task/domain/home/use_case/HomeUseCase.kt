package app.fawry.task.domain.home.use_case

import app.fawry.task.domain.auth.entity.UserModel
import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.auth.request.*
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.home.model.HomeResponse
import app.fawry.task.domain.home.model.match.MatchModel
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.notification.NotificationItem
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

  fun notification(): Flow<Resource<BaseResponse<ArrayList<NotificationItem>>>> = flow {
    emit(Resource.Loading) //show loader
    val result = repository.notifications()
    emit(result)//send result for collecting
  }.flowOn(Dispatchers.IO)


  fun home(): Flow<Resource<BaseResponse<HomeResponse>>> = flow {
    emit(Resource.Loading) //show loader
    val result = repository.home()
    emit(result)//send result for collecting
  }.flowOn(Dispatchers.IO)

  fun allMatches(): Flow<Resource<BaseResponse<ArrayList<MatchModel>>>> = flow {
    emit(Resource.Loading) //show loader
    val result = repository.allMatches()
    emit(result)//send result for collecting
  }.flowOn(Dispatchers.IO)

  fun matchDetails(id: Int): Flow<Resource<BaseResponse<MatchModel>>> = flow {
    emit(Resource.Loading) //show loader
    val result = repository.matchDetails(id)
    emit(result)//send result for collecting
  }.flowOn(Dispatchers.IO)


}