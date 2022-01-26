package com.example.testzemoga.data.di

import android.app.Application
import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource
import com.example.testzemoga.data.database.db.AppDataBase
import com.example.testzemoga.data.database.db.RoomDataSource
import com.example.testzemoga.data.server.networkAPI.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun dataBaseProvider(app: Application) = AppDataBase.getInstance(app.applicationContext)

    @Provides
    fun localDataSourceProvider(db: AppDataBase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = NetworkDataSource()
}