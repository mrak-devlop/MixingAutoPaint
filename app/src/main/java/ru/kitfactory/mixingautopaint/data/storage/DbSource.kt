package ru.kitfactory.mixingautopaint.data.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class DbSource private constructor(context: Context) {
    private val localDatabase : LocalDatabase = Room.databaseBuilder( context.applicationContext,
        LocalDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val paintDao = localDatabase.paintDao()
    private val executor = Executors.newSingleThreadExecutor()
    fun getPaints(): LiveData<List<Paint>> = paintDao.getPaints()
    fun getPaint(id: Int): LiveData<Paint?> = paintDao.getPaint(id)
    fun updatePaint(paint: Paint){
        executor.execute {
            paintDao.updatePaint(paint)
        }
    }
    fun addPaint(paint: Paint){
        executor.execute {
            paintDao.addPaint(paint)
        }
    }
    fun removePaint(id: Int){
        executor.execute {
            paintDao.removePaint(id)
        }
    }
    companion object {
        private var INSTANCE: DbSource? = null
        fun initialize(context: Context) { if (INSTANCE == null) {
            INSTANCE = DbSource(context) }
        }
        fun get(): DbSource {
            return INSTANCE ?:
            throw IllegalStateException("DbSource must be initialized")
        }
    }
}