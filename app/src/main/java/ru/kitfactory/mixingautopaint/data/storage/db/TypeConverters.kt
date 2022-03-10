package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.TypeConverter
import java.util.UUID

class TypeConverters {
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid) }
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString() }
}