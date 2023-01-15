package ru.kitfactory.mixingautopaint.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface DbDao {
    //получаем полный список из бд
    @Query("SELECT * FROM mix_paint_table ORDER BY id ASC")
    fun getPaints(): Flow<List<PaintDbModel>>

    //получаем конкретную краску по id
    @Query("SELECT * FROM mix_paint_table WHERE id=(:id)")
    fun getPaint(id: Int): Flow<PaintDbModel>

    //обновляем бд
    @Update
    fun updatePaint(paintDbModel: PaintDbModel)

    //добавляем в бд новый микс краски
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPaint(paintDbModel: PaintDbModel)

    @Delete
    suspend fun removePaint(paintDbModel: PaintDbModel)

}