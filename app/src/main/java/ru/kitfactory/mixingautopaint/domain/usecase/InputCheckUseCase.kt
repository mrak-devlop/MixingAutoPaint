package ru.kitfactory.mixingautopaint.domain.usecase

import ru.kitfactory.mixingautopaint.presentation.model.PaintForMix

class InputCheckUseCase(
    private val checkInputs: PaintForMix,
) {
    private val none = 0

    fun execute(): Boolean {
        return !(checkInputs.title.length == none &&
                checkInputs.paintPart.length == none &&
                checkInputs.hardenerPart.length == none &&
                checkInputs.diluentPart.length == none &&
                checkInputs.massPaint.length == none)
    }
}