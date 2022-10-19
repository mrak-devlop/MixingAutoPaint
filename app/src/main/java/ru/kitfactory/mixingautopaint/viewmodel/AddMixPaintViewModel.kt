package ru.kitfactory.mixingautopaint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class AddMixPaintViewModel(private val repository: LocalRepository) : ViewModel() {
    // добавляем данные в корутине
    fun addPaint(paint: Paint) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewPaint(paint)
        }
    }

}