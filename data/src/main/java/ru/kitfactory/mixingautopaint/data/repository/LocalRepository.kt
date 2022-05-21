package ru.kitfactory.mixingautopaint.data.repository

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class LocalRepository(private val dbDao: DbDao) {
    //получаем полный список красок
    val getPaints: LiveData<List<Paint>> = dbDao.getPaints()

    //добавление новой краски
    suspend fun addNewPaint(paint: Paint) {
        dbDao.addPaint(paint)
    }

    //получаем краску по индефикатору и удаляем её
    suspend fun removePaint(id: Int) {
        dbDao.removePaint(getPaints.value!![id])
    }

    suspend fun updatePaint(paint: Paint){
        dbDao.updatePaint(paint)
    }
}


