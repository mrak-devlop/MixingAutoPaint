package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Paint::class], version = 1)
@TypeConverters (TypeConverters::class)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun paintDao(): DbDao
}