package com.vpr.scheduleapp.data.di

import android.content.Context
import androidx.room.Room
import com.vpr.scheduleapp.data.remote.ScheduleApiService
import com.vpr.scheduleapp.data.database.ScheduleDatabase
import com.vpr.scheduleapp.data.repository.ScheduleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.rasp.yandex.net/v3.0/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideScheduleApiService(retrofit: Retrofit): ScheduleApiService = retrofit.create(ScheduleApiService::class.java)

    @Singleton
    @Provides
    fun provideScheduleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ScheduleDatabase::class.java,
            "schedule_database"
        ).build()

    @Singleton
    @Provides
    fun provideScheduleRepository(scheduleDatabase: ScheduleDatabase, scheduleApiService: ScheduleApiService) = ScheduleRepositoryImpl(scheduleDatabase, scheduleApiService)
}