package com.example.icebeth.core.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icebeth.core.data.database.model.ResultEntity
import com.example.icebeth.core.data.database.model.ResultWithMeasurements
import com.example.icebeth.core.data.database.model.SnowConditionDescription
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(resultEntity: ResultEntity): Long

    @Delete
    suspend fun deleteResult(resultEntity: ResultEntity)

    @Query("SELECT COUNT(*) FROM results WHERE remote_id IS NULL")
    fun getCountOfResultsWithNullRemoteId(): Flow<Int>

    @Query("SELECT * FROM results WHERE (remote_id IS NULL) OR (is_updated = 1)")
    fun getAllUnloadedResultsWithMeasurements(): List<ResultWithMeasurements>

    @Query("DELETE FROM results WHERE id = :resultId")
    suspend fun deleteResultById(resultId: Int)

    @Query(
        "UPDATE results SET " +
            "degree_of_coverage = :degreeOfCoverage, " +
            "snow_cover_character = :snowCoverCharacter, " +
            "snow_condition_description = :snowConditionDescription, " +
            "is_updated = :isUpdated " +
            "WHERE id = :resultId"
    )
    suspend fun updateResult(
        resultId: Int,
        degreeOfCoverage: Int,
        snowCoverCharacter: SnowCoverCharacter,
        snowConditionDescription: SnowConditionDescription,
        isUpdated: Boolean
    )

    @Query("UPDATE results SET remote_id = :remoteId, is_updated = 0 WHERE id = :resultId")
    suspend fun updateRemoteId(resultId: Int, remoteId: Int)

    @Query("SELECT * FROM results")
    fun getAllResults(): Flow<List<ResultEntity>>

    @Query("SELECT * FROM results WHERE id = :resultId")
    fun getResultWithMeasurementsById(resultId: Int): Flow<ResultWithMeasurements>

    @Query("UPDATE results SET is_updated = :isUpdated WHERE id = :resultId")
    suspend fun updateIsUpdated(resultId: Int, isUpdated: Boolean)
}
