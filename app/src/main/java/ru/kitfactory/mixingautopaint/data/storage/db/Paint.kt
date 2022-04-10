package ru.kitfactory.mixingautopaint.data.storage.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "mix_paint_table") // название таблицы бд
data class Paint(
    @PrimaryKey(autoGenerate = true)
    val id: Int, //уникальное поле
    var titleMix: String = "", // заголовок
    var partPaint : Int = 0, //количество частей краски
    var partHardener: Int = 0, // количество частей отвердителя
    var partDiluent: Int = 0, // колличество частей разбавителя
    var paintMass: Int = 0, // масса краски
    var paintMassForMix: Float = 0f,
    var massHardenerForMix: Float = 0f, // масса отвердителя
    var paintPlusHardener: Float = 0f, // масса краски плюс отвердитель
    var massDiluentForMix: Float = 0f // масса разбавителя

) : Parcelable
