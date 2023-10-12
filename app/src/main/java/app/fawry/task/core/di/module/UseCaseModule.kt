package app.fawry.task.core.di.module

import app.fawry.task.domain.auth.repository.AuthRepository
import app.fawry.task.domain.auth.use_case.AuthUseCase
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** provide module for repository in UseCase **/
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
  @Provides
  @Singleton
  fun provideAuthUseCase(
    repositoryRemote: AuthRepository,
    userLocalUseCase: UserLocalUseCase
  ): AuthUseCase = AuthUseCase(repositoryRemote, userLocalUseCase)


}