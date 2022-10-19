package ru.kitfactory.mixingautopaint.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.viewmodel.AddMixPaintViewModel
import ru.kitfactory.mixingautopaint.viewmodel.EditViewModel
import ru.kitfactory.mixingautopaint.viewmodel.PaintListViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val repository: LocalRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaintListViewModel::class.java)){
            return PaintListViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(AddMixPaintViewModel::class.java)){
            return AddMixPaintViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            return EditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct view-model")
    }
}