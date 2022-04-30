package ru.kitfactory.mixingautopaint.domain.usecase

import ru.kitfactory.mixingautopaint.domain.models.CompleteMixData

class CalcMixUseCase(
    private val paintPart: Int, private val hardenerPart: Int,
    private val diluentPart: Int, private val paintMass: Int
) {
    fun execute(): CompleteMixData {


        //считаем массу одной части
        val onePartMass =
            paintMass / (paintPart + hardenerPart + this.diluentPart)

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

        return CompleteMixData(
            massPaint.toFloat(),
            massHardener.toFloat(), paintPlusHardener.toFloat(),
            massDiluent.toFloat(), this.paintMass.toDouble().toFloat()
        )
    }

}

