package ru.kitfactory.domain.usecase

import ru.kitfactory.domain.PaintRepository
import ru.kitfactory.domain.models.PaintDomainModel

class UpdatePaintUseCase(private val repository: PaintRepository) {
    suspend fun execute(paint: PaintDomainModel){
        repository.updatePaint(paint)
    }
}