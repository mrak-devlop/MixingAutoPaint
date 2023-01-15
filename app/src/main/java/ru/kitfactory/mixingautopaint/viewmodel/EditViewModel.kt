package ru.kitfactory.mixingautopaint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.domain.usecase.UpdatePaintUseCase
import ru.kitfactory.mixingautopaint.data.repository.PaintRepositoryImpl

class EditViewModel(repository: PaintRepositoryImpl) : ViewModel() {
    private val updatePaintUseCase = UpdatePaintUseCase(repository)
    fun updatePaint(paint: PaintDomainModel){
        viewModelScope.launch(Dispatchers.IO) {
           updatePaintUseCase.execute(paint)
        }
    }
}