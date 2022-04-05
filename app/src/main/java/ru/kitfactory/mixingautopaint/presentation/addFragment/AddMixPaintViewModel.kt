package ru.kitfactory.mixingautopaint.presentation.addFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class AddMixPaintViewModel(application: Application) : AndroidViewModel(application) {
    // активация репозитория
    private var repository: LocalRepository
    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = LocalRepository(dbDao)
    }

    // добавляем данные в корутине
    fun addPaint(paint: Paint) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addNewPaint(paint)
        }
    }

}