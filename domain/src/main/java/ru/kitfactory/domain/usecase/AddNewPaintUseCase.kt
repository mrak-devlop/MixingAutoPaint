package ru.kitfactory.domain.usecase

import ru.kitfactory.domain.PaintRepository
import ru.kitfactory.domain.models.PaintDomainModel

class AddNewPaintUseCase(private val paintRepository: PaintRepository) {
    suspend fun execute(paint: PaintDomainModel){
        paintRepository.addNewPaint(paint)
    }
}