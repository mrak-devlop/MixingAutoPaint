package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Paint(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    var titleMix: String,
    var paintMass: Float,
    var massHardener: Float,
    var paintPlusHardener: Float,
    var massDiluent: Float,
    var massMix: Float,
)
