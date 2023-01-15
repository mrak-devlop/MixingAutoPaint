package ru.kitfactory.domain

import ru.kitfactory.domain.models.PaintDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PaintRepository {

    fun getPaints(): Flow<List<PaintDomainModel>>

    suspend fun addNewPaint(paint: PaintDomainModel)

    suspend fun removePaint(id: Int)

    suspend fun updatePaint(paint: PaintDomainModel)
}