package ru.kitfactory.mixingautopaint.data.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Paint::class], version = 1, exportSchema = false)
    abstract class LocalDatabase : RoomDatabase(){
    abstract fun dbDao(): DbDao
    companion object{
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        fun getDatabase(context: Context): LocalDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                LocalDatabase::class.java,
                "mix_paint_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}