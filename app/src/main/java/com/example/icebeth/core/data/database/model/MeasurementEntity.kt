package com.example.icebeth.core.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.icebeth.core.data.network.model.request.MeasurementCreateRequest
import com.example.icebeth.core.data.network.model.request.MeasurementUpdateRequest

@Entity(
    tableName = "measurements",
    foreignKeys = [
        ForeignKey(
            entity = ResultEntity::class,
            parentColumns = ["id"],
            childColumns = ["result_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "result_id", defaultValue = "1")
    val resultId: Int,
    @ColumnInfo(name = "remote_id")
    val remoteId: Int? = null,
    @ColumnInfo(name = "remote_result_id")
    val remoteResultId: Int? = null,

    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,
    @ColumnInfo(name = "is_updated")
    val isUpdated: Boolean = false,

    val time: Long,
    val latitude: Double,
    val longitude: Double,

    @ColumnInfo(name = "snow_height")
    val snowHeight: Int,

    @ColumnInfo(name = "cylinder_height")
    val cylinderHeight: Int?,
    @ColumnInfo(name = "mass_of_snow")
    val massOfSnow: Double?,

    @ColumnInfo(name = "soil_surface_condition")
    val soilSurfaceCondition: SoilSurfaceCondition?,
    @ColumnInfo(name = "snow_crust")
    val snowCrust: Boolean?,

    @ColumnInfo(name = "ice_crust_thickness")
    val iceCrustThickness: Int?,
    @ColumnInfo(name = "snow_layer_water_saturation")
    val snowLayerWaterSaturation: Int?,
    @ColumnInfo(name = "thawed_water_layer_thickness")
    val thawedWaterLayerThickness: Int?
)

enum class SoilSurfaceCondition(override val description: String) : Description {
    THAWED("Талая"),
    FROZEN_DRY_CEMENTED("Мерзлая сухая, сцементирована льдом, кристаллов льда не видно"),
    FROZEN_WEAKLY_CEMENTED("Мерзлая, слабо сцементирована льдом, не слитная, умеренно твердая"),
    FROZEN_MODERATELY_CEMENTED("Мерзлая, умеренно сцементированная льдом, слитная и твердая"),
    FROZEN_STRONGLY_CEMENTED("Мерзлая, сильно сцементирована льдом, очень слитная и твердая"),
    UNKNOWN("Неизвестно")
}

fun MeasurementEntity.asCreateRequest(remoteResultId: Int) = MeasurementCreateRequest(
    resultId = remoteResultId,
    time = time,
    latitude = latitude,
    longitude = longitude,
    snowHeight = snowHeight,
    cylinderHeight = cylinderHeight,
    massOfSnow = massOfSnow,
    soilSurfaceCondition = soilSurfaceCondition?.ordinal,
    snowCrust = snowCrust,
    iceCrustThickness = iceCrustThickness,
    snowLayerWaterSaturation = snowLayerWaterSaturation,
    thawedWaterLayerThickness = thawedWaterLayerThickness
)

fun MeasurementEntity.asUpdateRequest() = MeasurementUpdateRequest(
    snowHeight = snowHeight,
    cylinderHeight = cylinderHeight,
    massOfSnow = massOfSnow,
    soilSurfaceCondition = soilSurfaceCondition?.ordinal,
    snowCrust = snowCrust,
    iceCrustThickness = iceCrustThickness,
    snowLayerWaterSaturation = snowLayerWaterSaturation,
    thawedWaterLayerThickness = thawedWaterLayerThickness
)

interface Description {
    val description: String
}
