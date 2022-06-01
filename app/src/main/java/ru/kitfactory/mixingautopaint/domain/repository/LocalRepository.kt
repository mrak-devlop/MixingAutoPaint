package ru.kitfactory.mixingautopaint.domain.repository

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

interface LocalRepository {
    val getPaints: LiveData<List<Paint>>

    //добавление новой краски
    suspend fun addNewPaint(paint: Paint)

    //получаем краску по индефикатору и удаляем её
    suspend fun removePaint(id: Int)

    suspend fun updatePaint(paint: Paint)
}