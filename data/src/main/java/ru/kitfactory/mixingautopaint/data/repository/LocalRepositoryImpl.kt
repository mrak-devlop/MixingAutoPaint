package ru.kitfactory.mixingautopaint.data.repository

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.repository.LocalRepository

class LocalRepositoryImpl(private val dbDao: DbDao): LocalRepository{
    //получаем полный список красок

    override val getPaints: LiveData<List<Paint>> = dbDao.getPaints()


    //добавление новой краски
    override suspend fun addNewPaint(paint: Paint) {
        dbDao.addPaint(paint)
    }

    //получаем краску по индефикатору и удаляем её
    override suspend fun removePaint(id: Int) {
        dbDao.removePaint(getPaints.value!![id])
    }

    override suspend fun updatePaint(paint: Paint){
        dbDao.updatePaint(paint)
    }
}



