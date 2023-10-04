package com.example.icebeth.core.database

import com.example.icebeth.core.database.dao.MeasurementDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesMeasurementDao(
        database: AppDatabase
    ): MeasurementDao = database.measurementDao()
}