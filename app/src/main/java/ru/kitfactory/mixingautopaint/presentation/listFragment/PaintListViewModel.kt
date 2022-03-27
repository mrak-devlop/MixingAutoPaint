package ru.kitfactory.mixingautopaint.presentation.listFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.Paint


class PaintListViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Paint>>
    private var repository: LocalRepository

    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = LocalRepository(dbDao)
        readAllData = repository.getPaints
    }

}