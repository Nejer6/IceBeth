package com.example.icebeth.core.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.icebeth.core.data.database.dao.MeasurementDao
import com.example.icebeth.core.data.database.dao.ResultDao
import com.example.icebeth.core.data.database.model.MeasurementEntity
import com.example.icebeth.core.data.database.model.ResultEntity

@Database(
    version = 7,
    exportSchema = true,
    entities = [
        MeasurementEntity::class,
        ResultEntity::class
    ],
    autoMigrations = [
        AutoMigration(from = 3, to = 4, spec = DatabaseMigrations.Schema3to4::class),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6, spec = DatabaseMigrations.Schema5to6::class),
        AutoMigration(from = 6, to = 7)
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
    abstract fun resultDao(): ResultDao
}
