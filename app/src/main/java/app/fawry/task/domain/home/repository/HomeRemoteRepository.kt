package app.fawry.task.domain.home.repository
import app.fawry.task.domain.auth.entity.UserModel
import app.fawry.task.domain.utils.Resource
import app.fawry.task.domain.auth.request.LogInRequest

interface HomeRemoteRepository {
  suspend fun home(): Resource<List<UserModel>>
}