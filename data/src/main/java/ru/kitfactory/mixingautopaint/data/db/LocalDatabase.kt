package ru.kitfactory.mixingautopaint.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PaintDbModel::class], version = 2, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun dbDao(): DbDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        fun getDatabase(context: Context): LocalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "mix_paint_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}