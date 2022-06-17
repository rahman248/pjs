package com.example.test.di

import com.example.test.data.UserRepositoryImpl
import com.example.test.data.remote.ApiService
import com.example.test.data.remote.source.AuthRemoteDataSource
import com.example.test.data.repo.UsersRepository
import com.example.test.utils.ContextProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideContextProvider(): ContextProviders = ContextProviders.getInstance()

    @Singleton
    @Provides
    fun providePrayerRepositoryImpl(
        contextProviders: ContextProviders,
        remoteDataSource: AuthRemoteDataSource
    ) = UserRepositoryImpl(contextProviders, remoteDataSource) as UsersRepository




}