package ru.kitfactory.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.kitfactory.domain.PaintRepository
import ru.kitfactory.domain.models.PaintDomainModel

class GetPaintsUseCase(private val paintRepository: PaintRepository) {
    fun execute(): Flow<List<PaintDomainModel>> {
       return paintRepository.getPaints()
    }
}