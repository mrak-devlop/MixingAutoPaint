package ru.kitfactory.mixingautopaint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.Paint


class PaintListViewModel(private val repository: LocalRepository) : ViewModel() {
    //активируем и считываем данные из репозитория
    val readAllData: LiveData<List<Paint>> = repository.getPaints

    // удаляем данные из репозитория в корутине
    fun removePaint(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removePaint(id)
        }
    }



}