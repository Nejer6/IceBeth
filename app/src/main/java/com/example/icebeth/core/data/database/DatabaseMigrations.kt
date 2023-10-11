package com.example.icebeth.core.data.database

import androidx.room.DeleteColumn
import androidx.room.migration.AutoMigrationSpec

object DatabaseMigrations {

    @DeleteColumn(
        tableName = "measurements",
        columnName = "is_uploaded"
    )
    class Schema3to4 : AutoMigrationSpec
}