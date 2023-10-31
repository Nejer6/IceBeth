package com.example.icebeth.core.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val time: Long,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
    @ColumnInfo(name = "remote_id")
    val remoteId: Int?,

    @ColumnInfo(name = "degree_of_coverage")
    val degreeOfCoverage: Int?,
    @ColumnInfo(name = "snow_cover_character")
    val snowCoverCharacter: SnowCoverCharacter?,
    @ColumnInfo(name = "snow_condition_description")
    val snowConditionDescription: SnowStructure?
)

enum class SnowCoverCharacter(val description: String) {
    UNIFORM_FROZEN_SOIL("Равномерный, почва замерзшая"),
    UNIFORM_THAWED_SOIL("Равномерный, почва оттаявшая"),
    UNIFORM_UNKNOWN_SOIL("Равномерный, состояние почвы неизвестно"),
    UNEVEN_FROZEN_SOIL("Неравномерный, почва замерзшая"),
    UNEVEN_THAWED_SOIL("Неравномерный, почва оттаявшая"),
    UNEVEN_UNKNOWN_SOIL("Неравномерный, состояние почвы неизвестно"),
    VERY_UNEVEN_FROZEN_SOIL("Очень неравномерный, почва замерзшая"),
    VERY_UNEVEN_THAWED_SOIL("Очень неравномерный, почва оттаявшая")
}

enum class SnowStructure(val description: String) {
    FRESH_POWDERY("Свежий пылевидный"),
    FRESH_FLUFFY("Свежий пушистый"),
    FRESH_STICKY("Свежий липкий"),
    OLD_CRUMBLY("Старый рассыпчатый"),
    OLD_COMPACT("Старый плотный"),
    OLD_MOIST("Старый влажный"),
    UNBOUNDED_CRUST("Корка несвязанная"),
    COMPACTED_CRUST("Плотный с коркой"),
    MOIST_CRUST("Влажный с коркой"),
    WATER_SATURATED("Насыщенный водой")
}