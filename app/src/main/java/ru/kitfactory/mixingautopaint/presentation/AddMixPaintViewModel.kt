package ru.kitfactory.mixingautopaint.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kitfactory.mixingautopaint.data.storage.DbSource
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class AddMixPaintViewModel(application: Application) : AndroidViewModel(application) {
    private val getPaints: LiveData<List<Paint>>
    private var repository: DbSource

    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = DbSource(dbDao)
        getPaints = repository.getPaints
    }

    fun addPaint(paint: Paint) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addPaint(paint)
        }
    }

}