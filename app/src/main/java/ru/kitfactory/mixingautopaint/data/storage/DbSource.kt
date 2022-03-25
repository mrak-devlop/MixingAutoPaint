package ru.kitfactory.mixingautopaint.data.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import java.util.concurrent.Executors


class DbSource (private val dbDao: DbDao) {
    val getPaints: LiveData<List<Paint>> = dbDao.getPaints()

    suspend fun addPaint(paint: Paint){
        dbDao.addPaint(paint)
    }

}