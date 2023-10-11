package app.fawry.task.domain.auth.repository
import app.fawry.task.domain.auth.entity.UserModel
import app.fawry.task.domain.utils.Resource
import app.fawry.task.domain.auth.request.LogInRequest

interface AuthRemoteRepository {
  suspend fun login(request: LogInRequest): Resource<UserModel>
}