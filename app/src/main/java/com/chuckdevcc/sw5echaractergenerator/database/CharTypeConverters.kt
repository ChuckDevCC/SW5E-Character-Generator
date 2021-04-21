package com.chuckdevcc.sw5echaractergenerator.database

import androidx.room.TypeConverter
import java.util.*

// converts the uuid type to string, and vice versa.
class CharTypeConverters {
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}