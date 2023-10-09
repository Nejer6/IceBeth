package com.example.icebeth.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.model.MeasurementEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [MeasurementEntity::class]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
}