package ru.kitfactory.mixingautopaint.data.repository

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class LocalRepository(private val dbDao: DbDao){
    val getPaints : LiveData<List<Paint>> = dbDao.getPaints()

    suspend fun addNewPaint (paint: Paint){
        dbDao.addPaint(paint)
    }

    suspend fun removePaint (paint: Paint){
        dbDao.removePaint(paint)
    }
    suspend fun removePaint(id:Int){
     dbDao.removePaint(getPaints.value!![id])
    }
}


