package com.example.icebeth.core.data.database

import androidx.room.DeleteColumn
import androidx.room.migration.AutoMigrationSpec

object DatabaseMigrations {

    @DeleteColumn(
        tableName = "measurements",
        columnName = "is_uploaded"
    )
    class Schema3to4 : AutoMigrationSpec

    @DeleteColumn.Entries(
        DeleteColumn(
            tableName = "measurements",
            columnName = "ground_frozzed"
        ),
        DeleteColumn(
            tableName = "measurements",
            columnName = "resultId"
        ),
        DeleteColumn(
            tableName = "results",
            columnName = "is_active"
        )
    )
    class Schema5to6 : AutoMigrationSpec
}
