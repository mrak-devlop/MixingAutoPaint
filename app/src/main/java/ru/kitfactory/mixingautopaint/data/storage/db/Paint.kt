package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mix_auto_paint") // название таблицы бд
data class Paint(
    @PrimaryKey(autoGenerate = true) val id: Int, //уникальное поле
    var titleMix: String, // заголовок
    val partPaint : Int, //количество частей краски
    val partHardener: Int, // количество частей отвердителя
    val partDiluent: Int, // колличество частей разбавителя
    var paintMass: Float, // масса краски
    var massHardener: Float, // масса отвердителя
    var paintPlusHardener: Float, // масса краски плюс отвердитель
    var massDiluent: Float, // масса разбавителя
    var massMix: Float, // масса готовой краски

)
