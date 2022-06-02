package ru.kitfactory.mixingautopaint.domain.usecase

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.domain.repository.LocalRepository

class ReadAllPaintsUseCase(private val repository: LocalRepository) {
    fun execute(): LiveData<List<Paint>> {
        return repository.getPaints
    }
}