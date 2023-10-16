package app.fawry.task.core.di.module

import app.fawry.task.data.auth.AuthServices
import app.fawry.task.data.home.HomeServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/** provide Module NetworkServicesModule for inject retrofit module in Movie-Services  **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

  @Provides
  @Singleton
  fun provideAuthServices(retrofit: Retrofit): AuthServices =
    retrofit.create(AuthServices::class.java)

  @Provides
  @Singleton
  fun provideHomeServices(retrofit: Retrofit): HomeServices =
    retrofit.create(HomeServices::class.java)

}