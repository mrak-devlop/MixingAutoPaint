package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.TypeConverter
import java.util.UUID

class TypeConverters {
    @TypeConverter
    fun toId(id: String?): Int? {
        return id?.toInt() }
    @TypeConverter
    fun fromUUID(id: Int?): String? {
        return id?.toString() }
}