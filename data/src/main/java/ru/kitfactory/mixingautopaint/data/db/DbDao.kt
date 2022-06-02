package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DbDao {
    //получаем полный список из бд
    @Query("SELECT * FROM mix_paint_table ORDER BY id ASC")
    fun getPaints(): LiveData<List<PaintImpl>>

    //получаем конкретную краску по id
    @Query("SELECT * FROM mix_paint_table WHERE id=(:id)")
    fun getPaint(id: Int): LiveData<PaintImpl?>

    //обновляем бд
    @Update
    suspend fun updatePaint(paint: PaintImpl)

    //добавляем в бд новый микс краски
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPaint(paint: PaintImpl)

    @Delete
    suspend fun removePaint(paint: PaintImpl)

}