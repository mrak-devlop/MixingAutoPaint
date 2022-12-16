package ru.kitfactory.domain

import androidx.lifecycle.LiveData
import ru.kitfactory.domain.models.PaintModel

interface PaintRepository {
    val getPaints: LiveData<List<PaintModel>>

    suspend fun addNewPaint(paint: PaintModel)

    suspend fun removePaint(paint: PaintModel)

    suspend fun updatePaint(ppaint: PaintModel)
}