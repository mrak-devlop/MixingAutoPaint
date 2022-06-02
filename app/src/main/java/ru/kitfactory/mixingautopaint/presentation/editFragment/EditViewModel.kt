package ru.kitfactory.mixingautopaint.presentation.editFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.data.repository.LocalRepositoryImpl
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.domain.usecase.UpdatePaintUseCase

class EditViewModel (application: Application) : AndroidViewModel(application) {
    // активация репозитория
    private var repository: LocalRepositoryImpl

    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = LocalRepositoryImpl(dbDao)
    }
    private val updatePaintUseCase = UpdatePaintUseCase(repository, EditViewModel(application))
    fun updatePaint(paint: Paint){
        updatePaintUseCase.execute(paint)
    }
}