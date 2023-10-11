package app.fawry.task.domain.utils

sealed class Resource<out T> {

  class Success<out T>(val value: T) : Resource<T>()

  class Failure(
    val failureStatus: FailureStatus,
    val code: Int? = null,
    val message: String? = null
  ) : Resource<Nothing>()

  object Loading : Resource<Nothing>()
  object HideProgress : Resource<Nothing>()

  object Default : Resource<Nothing>()
}