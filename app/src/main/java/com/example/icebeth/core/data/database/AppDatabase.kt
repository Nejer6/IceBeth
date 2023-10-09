package com.example.icebeth.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.dao.ResultDao
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.ResultEntity

@Database(
    version = 2,
    exportSchema = true,
    entities = [
        MeasurementEntity::class,
        ResultEntity::class
    ],

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
    abstract fun resultDao(): ResultDao
}