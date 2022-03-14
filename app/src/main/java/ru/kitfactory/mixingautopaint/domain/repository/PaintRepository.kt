package ru.kitfactory.mixingautopaint.domain.repository

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.storage.db.Paint


interface PaintRepository {
    fun getPaints(): LiveData<List<Paint>>
    fun getPaint(id: Int):LiveData<Paint?>
    fun removePaint(id: Int)
    fun saveNewPaint(paint: Paint)
}