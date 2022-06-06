package ru.kitfactory.mixingautopaint.domain.usecase

import ru.kitfactory.mixingautopaint.domain.models.CompleteMixData
import ru.kitfactory.mixingautopaint.domain.models.PaintForMix

class CalcMixUseCase(private val inputForMix: ru.kitfactory.mixingautopaint.domain.models.PaintForMix) {
    fun execute(): CompleteMixData {
        val inPaintMass = inputForMix.massPaint.toInt()
        val paintPart = inputForMix.paintPart.toFloat()
        val hardenerPart = inputForMix.hardenerPart.toFloat()
        val diluentPart = inputForMix.diluentPart.toFloat()

        //считаем массу одной части
        val onePartMass =
            inPaintMass / (paintPart + hardenerPart + diluentPart)

        //считаем массу краски и округляем до одного знака после запятой
        val massPaint =
            Math.round((onePartMass * paintPart) * 10.0) / 10.0

        //считаем массу отвердителя и округляем до одного знака после запятой
        val massHardener =
            Math.round((onePartMass * hardenerPart) * 10.0) / 10.0

        //считаем массу разбавителя и округляем до одного знака после запятой
        val massDiluent =
            Math.round((onePartMass * diluentPart) * 10.0) / 10.0
        // краска плюс отвердитель
        val paintPlusHardener = massPaint + massHardener

        val massMix = paintPlusHardener + massDiluent

        return CompleteMixData(
            massPaint.toFloat(),
            massHardener.toFloat(), paintPlusHardener.toFloat(),
            massDiluent.toFloat(), massMix.toFloat()
        )
    }

}

