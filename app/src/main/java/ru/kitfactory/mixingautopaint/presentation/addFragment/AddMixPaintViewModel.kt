package ru.kitfactory.mixingautopaint.presentation.addFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.kitfactory.mixingautopaint.data.repository.LocalRepositoryImpl
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.PaintImpl
import ru.kitfactory.mixingautopaint.domain.usecase.AddPaintUseCase

class AddMixPaintViewModel(application: Application) : AndroidViewModel(application) {
    // активация репозитория
    private var repository: LocalRepositoryImpl

    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = LocalRepositoryImpl(dbDao)
    }

    // добавляем данные в корутине
    private val addPaintUseCase = AddPaintUseCase(repository, AddMixPaintViewModel(application))
    fun addPaint(paint: PaintImpl) {
       addPaintUseCase.execute(paint)
    }

}