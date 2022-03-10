package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Dao
import androidx.room.Query
import java.util.UUID

@Dao
interface DbDao {
    @Query("SELECT * FROM paint")
    fun getPaints(): List<Paint>
    @Query("SELECT * FROM paint WHERE uid=(:id)")
    fun getPaint(id: UUID): Paint?
}