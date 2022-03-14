package ru.kitfactory.mixingautopaint.data.repository

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.models.PaintMix
import ru.kitfactory.mixingautopaint.data.storage.DbSource
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.models.CompleteMixData
import ru.kitfactory.mixingautopaint.domain.models.PaintsMix
import ru.kitfactory.mixingautopaint.domain.repository.PaintRepository
import java.util.*

class PaintRepositoryImpl: PaintRepository {
    private val dbSource = DbSource

    //получение краски по id
    override fun getPaint(id:Int):LiveData<Paint?> {
      return dbSource.get().getPaint(id)
    }

    //получение всего списка красок
    override fun getPaints(): LiveData<List<Paint>> {
        return dbSource.get().getPaints()
    }

    //удаляем запись по id
    override fun removePaint(id: Int) {
        dbSource.get().removePaint(id)
    }

    //сохраняем новую запись в bd
    override fun saveNewPaint(paint: Paint) {
        dbSource.get().addPaint(paint)
    }
}