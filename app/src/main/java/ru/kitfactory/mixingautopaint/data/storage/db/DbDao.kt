package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DbDao {
    //получаем полный список из бд
    @Query("SELECT * FROM mix_auto_paint")
    fun getPaints(): LiveData<List<Paint>>

    //получаем конкретную краску по id
    @Query("SELECT * FROM mix_auto_paint WHERE id=(:id)")
    fun getPaint(id: Int):LiveData<Paint?>

    //обновляем бд
    @Update
    fun updatePaint(paint: Paint)

    //добавляем в бд новый микс краски
    @Insert
    fun addPaint(paint: Paint)

    //удаляем строку по id
    @Query("DELETE FROM mix_auto_paint WHERE id=(:id)")
    fun removePaint(id: Int):LiveData<Paint?>
}