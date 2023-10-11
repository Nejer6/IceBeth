package com.example.icebeth.core.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.icebeth.core.model.Result

@Entity(tableName = "results")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Long,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
    @ColumnInfo(name = "remote_id")
    val remoteId: Int?
)

fun ResultEntity.asExternalModel() = Result(
    id = id,
    time = time,
    isActive = isActive,
    remoteId = remoteId
)
