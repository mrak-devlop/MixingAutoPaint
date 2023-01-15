package ru.kitfactory.mixingautopaint.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.domain.usecase.GetPaintsUseCase
import ru.kitfactory.domain.usecase.RemovePaintUseCase
import ru.kitfactory.mixingautopaint.data.repository.PaintRepositoryImpl
import ru.kitfactory.mixingautopaint.models.PaintAppModel
import ru.kitfactory.mixingautopaint.presentation.listFragment.PaintListFragmentDirections


class PaintListViewModel(repository: PaintRepositoryImpl) : ViewModel() {
    private val getPaintsUseCase = GetPaintsUseCase(repository)
    private val removePaintUseCase = RemovePaintUseCase(repository)

    val allPaints = convertModelDomainToApp(getPaintsUseCase.execute()).asLiveData()
    private fun convertModelDomainToApp(
        domainModel: Flow<List<PaintDomainModel>>
    ): Flow<List<PaintAppModel>> {
        return domainModel.map { model ->
            model.map {
                PaintAppModel(
                    id = it.id,
                    titleMix = it.titleMix,
                    partPaint = it.partPaint,
                    partHardener = it.partHardener,
                    partDiluent = it.partDiluent,
                    paintMass = it.paintMass,
                    paintMassForMix = it.paintMassForMix,
                    massHardenerForMix = it.massHardenerForMix,
                    paintPlusHardener = it.paintPlusHardener,
                    massDiluentForMix = it.massDiluentForMix
                )
            }
        }
    }


    // удаляем данные из репозитория в корутине
    fun removePaint(id: Int) {
        viewModelScope.launch {
            removePaintUseCase.execute(id)
        }
    }

    fun onFabButtonClick(view: View) {
        val directions = PaintListFragmentDirections.actionPaintListFragmentToAddMixPaintFragment()
        Navigation.findNavController(view).navigate(directions)
    }


}