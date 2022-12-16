package ru.kitfactory.mixingautopaint.data

import androidx.lifecycle.LiveData
import ru.kitfactory.domain.PaintRepository
import ru.kitfactory.domain.models.PaintModel

class PaintRepositoryImpl():PaintRepository {
    override val getPaints: LiveData<List<PaintModel>>
        get() {
            TODO()
        }

    override suspend fun addNewPaint(paint: PaintModel) {
        TODO("Not yet implemented")
    }

    override suspend fun removePaint(paint: PaintModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePaint(ppaint: PaintModel) {
        TODO("Not yet implemented")
    }
}