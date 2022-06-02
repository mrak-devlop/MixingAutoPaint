package ru.kitfactory.mixingautopaint.presentation.listFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.repository.LocalRepositoryImpl
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase
import ru.kitfactory.mixingautopaint.data.storage.db.PaintImpl
import ru.kitfactory.mixingautopaint.domain.repository.LocalRepository
import ru.kitfactory.mixingautopaint.domain.usecase.ReadAllPaintsUseCase
import ru.kitfactory.mixingautopaint.domain.usecase.RemovePaintUseCase


class PaintListViewModel(application: Application) : AndroidViewModel(application) {
    //активируем и считываем данные из репозитория
    private var repository: LocalRepository

    init {
        val dbDao = LocalDatabase.getDatabase(application).dbDao()
        repository = LocalRepositoryImpl(dbDao)
    }

    private val readAllPaintsUseCase = ReadAllPaintsUseCase(repository)
    val readAllData: LiveData<List<PaintImpl>> = readAllPaintsUseCase.execute()

    // удаляем данные из репозитория в корутине
    private val removesPaintUseCase = RemovePaintUseCase(repository, PaintListViewModel(application))
    fun removePaint(id: Int) {
        removesPaintUseCase.execute(id)
    }


}