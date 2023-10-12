package app.fawry.task.core.di.module

import app.fawry.task.data.auth.AuthRemoteDataSource
import app.fawry.task.data.auth.AuthRepositoryImpl
import app.fawry.task.data.local.preferences.AppPreferences
import app.fawry.task.data.repository.AccountRepositoryImpl
import app.fawry.task.domain.auth.repository.AuthRepository
import app.fawry.task.domain.category.repository.CategoryRemoteRepository
import app.fawry.task.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** provide module for repository to use remoteDataSource in Repository **/
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)


  @Provides
  @Singleton
  fun provideAccountRepository(
    appPreferences: AppPreferences
  ): AccountRepository = AccountRepositoryImpl(appPreferences)
}