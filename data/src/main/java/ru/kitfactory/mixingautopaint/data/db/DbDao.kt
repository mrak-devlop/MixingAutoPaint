package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DbDao {
    //получаем полный список из бд
    @Query("SELECT * FROM mix_paint_table ORDER BY id ASC")
    fun getPaints(): LiveData<List<Paint>>

    //получаем конкретную краску по id
    @Query("SELECT * FROM mix_paint_table WHERE id=(:id)")
    fun getPaint(id: Int): LiveData<Paint?>

    //обновляем бд
    @Update
    suspend fun updatePaint(paint: Paint)

    //добавляем в бд новый микс краски
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPaint(paint: Paint)

    @Delete
    suspend fun removePaint(paint: Paint)

}