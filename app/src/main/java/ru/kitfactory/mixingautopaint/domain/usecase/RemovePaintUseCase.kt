package ru.kitfactory.mixingautopaint.domain.usecase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.domain.repository.LocalRepository

class RemovePaintUseCase(
    private val repository: LocalRepository,
    private val viewModel: ViewModel
) {
    fun execute(id: Int) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            repository.removePaint(id)
        }

    }
}