package ru.kitfactory.mixingautopaint.domain.usecase

import ru.kitfactory.mixingautopaint.data.repository.PaintRepositoryImpl
import ru.kitfactory.mixingautopaint.data.storage.db.Paint

class SaveNewPaintUseCase(paint: Paint) {
    private val paint = paint
    fun execute(){
      //  PaintRepositoryImpl().saveNewPaint(paint)
    }
}