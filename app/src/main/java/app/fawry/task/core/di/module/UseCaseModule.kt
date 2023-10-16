package app.fawry.task.core.di.module

import app.fawry.task.domain.home.repository.HomeRepository
import app.fawry.task.domain.home.use_case.HomeUseCase
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
    repositoryRemote: HomeRepository,
    userLocalUseCase: UserLocalUseCase
  ): HomeUseCase = HomeUseCase(repositoryRemote, userLocalUseCase)



}