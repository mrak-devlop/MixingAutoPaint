package ru.kitfactory.mixingautopaint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.domain.usecase.AddNewPaintUseCase
import ru.kitfactory.mixingautopaint.data.repository.PaintRepositoryImpl

class AddMixPaintViewModel(repository: PaintRepositoryImpl) : ViewModel() {
    private val addNewPaintUseCase = AddNewPaintUseCase(repository)
    // добавляем данные в корутине
    fun addPaint(paint: PaintDomainModel) {
        viewModelScope.launch(Dispatchers.IO) {
           addNewPaintUseCase.execute(paint)
        }
    }

}