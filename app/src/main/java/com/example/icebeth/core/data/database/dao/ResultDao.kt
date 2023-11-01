package com.example.icebeth.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.data.database.model.ResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(resultEntity: ResultEntity)

    @Delete
    suspend fun deleteResult(resultEntity: ResultEntity)

    @Query("SELECT COUNT(*) FROM results WHERE remote_id IS NULL")
    fun getCountOfResultsWithNullRemoteId(): Flow<Int>
}