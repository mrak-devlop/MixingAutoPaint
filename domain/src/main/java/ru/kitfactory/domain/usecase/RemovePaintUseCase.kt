package ru.kitfactory.domain.usecase

import ru.kitfactory.domain.PaintRepository

class RemovePaintUseCase(private val paintRepository: PaintRepository) {
    suspend fun execute(id: Int){
        paintRepository.removePaint(id)
    }
}