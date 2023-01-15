package ru.kitfactory.mixingautopaint.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kitfactory.domain.models.PaintDomainModel


@Entity(tableName = "mix_paint_table") // название таблицы бд
data class PaintDbModel(
    @PrimaryKey(autoGenerate = true)
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

fun List<PaintDbModel>.asDomainModel(): List<PaintDomainModel>{
    return map {
        PaintDomainModel(
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