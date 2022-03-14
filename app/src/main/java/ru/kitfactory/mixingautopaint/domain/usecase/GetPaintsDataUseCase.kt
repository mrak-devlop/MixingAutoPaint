package ru.kitfactory.mixingautopaint.domain.usecase

import androidx.lifecycle.LiveData
import ru.kitfactory.mixingautopaint.data.repository.PaintRepositoryImpl
import ru.kitfactory.mixingautopaint.data.storage.db.Paint
import ru.kitfactory.mixingautopaint.domain.models.CompleteMixData
import ru.kitfactory.mixingautopaint.domain.repository.PaintRepository


class GetPaintsDataUseCase {
    

    fun getPaintsData(rep: PaintRepository) : LiveData<List<Paint>> {
        return rep.getPaints()

    }
}