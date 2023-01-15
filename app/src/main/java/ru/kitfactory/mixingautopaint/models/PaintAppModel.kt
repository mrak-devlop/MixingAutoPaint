package ru.kitfactory.mixingautopaint.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.kitfactory.domain.models.PaintDomainModel

@Parcelize
data class PaintAppModel(
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
) : Parcelable