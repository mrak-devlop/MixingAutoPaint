package ru.kitfactory.mixingautopaint.domain.usecase

import ru.kitfactory.mixingautopaint.domain.models.CompleteMixData
import ru.kitfactory.mixingautopaint.domain.models.DataMix

class CalcMixUseCase(private val dataMix :  DataMix) {
    fun execute() : CompleteMixData {
        val paintPart = dataMix.paintPart
        val hardenerPart = dataMix.hardenerPart
        val diluentPart = dataMix.diluentPart
        val paintMass = dataMix.paintMass

        //считаем массу одной части
        val onePartMass =
            paintMass / (paintPart + hardenerPart + diluentPart)

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

        return CompleteMixData(massPaint,
            massHardener, paintPlusHardener,
            massDiluent, paintMass.toDouble())
    }

}

