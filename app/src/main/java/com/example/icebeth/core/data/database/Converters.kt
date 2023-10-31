package com.example.icebeth.core.data.database

import androidx.room.TypeConverter
import com.example.icebeth.core.data.database.model.SnowCoverCharacter
import com.example.icebeth.core.data.database.model.SnowStructure
import com.example.icebeth.core.data.database.model.SoilSurfaceCondition

class Converters {
    @TypeConverter
    fun toSnowCoverCharacter(value: String) = enumValueOf<SnowCoverCharacter>(value)
    @TypeConverter
    fun fromSnowCoverCharacter(value: SnowCoverCharacter) = value.name

    @TypeConverter
    fun toSnowStructure(value: String) = enumValueOf<SnowStructure>(value)
    @TypeConverter
    fun fromSnowStructure(value: SnowStructure) = value.name

    @TypeConverter
    fun toSoilSurfaceCondition(value: String) = enumValueOf<SoilSurfaceCondition>(value)
    @TypeConverter
    fun fromSoilSurfaceCondition(value: SoilSurfaceCondition) = value.name
}