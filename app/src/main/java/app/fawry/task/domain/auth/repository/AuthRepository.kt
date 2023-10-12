package app.fawry.task.domain.auth.repository

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource

interface AuthRepository {

  suspend fun login(request: LogInRequest): Resource<BaseResponse<LoginResponse>>
  suspend fun register(request: RegisterRequest): Resource<BaseResponse<LoginResponse>>

}