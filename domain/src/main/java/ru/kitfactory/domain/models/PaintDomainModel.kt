package ru.kitfactory.domain.models

data class PaintDomainModel (
    val id: Int, //уникальное поле
    var titleMix: String = "", // заголовок
    var partPaint: Float = 0f, //количество частей краски
    var partHardener: Float = 0f, // количество частей отвердителя
    var partDiluent: Float = 0f, // колличество частей разбавителя
    var paintMass: Int = 0, // масса краски
    var paintMassForMix: Float = 0f,
    var massHardenerForMix: Float = 0f, // масса отвердителя
    var paintPlusHardener: Float = 0f, // масса краски плюс отвердитель
    var massDiluentForMix: Float = 0f // масса разбавителя
        )
