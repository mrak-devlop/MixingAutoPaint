package ru.kitfactory.mixingautopaint.presentation.editFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase

class EditViewModel (application: Application) : AndroidViewModel(application) {
    // активация репозитория
    private var repository: LocalRepository

    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = LocalRepository(dbDao)
    }

    fun updatePaint(paint: Paint){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePaint(paint)
        }
    }
}