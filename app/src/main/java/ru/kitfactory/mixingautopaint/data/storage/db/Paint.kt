package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mix_auto_paint") // название таблицы бд
data class Paint(
    @PrimaryKey(autoGenerate = true) val id: Int, //уникальное поле
    var titleMix: String, // заголовок
    var partPaint : Int, //количество частей краски
    var partHardener: Int, // количество частей отвердителя
    var partDiluent: Int, // колличество частей разбавителя
    var paintMass: Int, // масса краски
    var massHardener: Float, // масса отвердителя
    var paintPlusHardener: Float, // масса краски плюс отвердитель
    var massDiluent: Float, // масса разбавителя

)
