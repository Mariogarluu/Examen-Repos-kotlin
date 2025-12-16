package com.turingalan.examen.di

import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.remote.BookRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindBookDataSource(impl: BookRemoteDataSourceImpl): BookDataSource
}