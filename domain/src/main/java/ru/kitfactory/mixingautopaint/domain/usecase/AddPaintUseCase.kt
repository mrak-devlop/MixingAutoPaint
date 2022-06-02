package ru.kitfactory.mixingautopaint.domain.usecase

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.domain.repository.LocalRepository

class AddPaintUseCase(
    private val repository: LocalRepository,
    private val viewModel: AndroidViewModel
) {
    fun execute(paint: Paint) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            repository.addNewPaint(paint)
        }
    }
}