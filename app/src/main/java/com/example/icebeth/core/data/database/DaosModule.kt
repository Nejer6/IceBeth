package com.example.icebeth.core.data.database

import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.dao.ResultDao
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

    @Provides
    fun providesResultDao(
        database: AppDatabase
    ): ResultDao = database.resultDao()
}
