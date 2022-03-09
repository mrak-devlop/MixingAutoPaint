package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase

class PaintDatabase {
    @Database(entities = [Paint::class], version = 1)
    abstract class PaintDatabase : RoomDatabase(){}
}