package ru.kitfactory.mixingautopaint.data.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Paint(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    var paintMass : Double,
    var massHardener : Double,
    var paintPlusHardener : Double,
    var massDiluent: Double,
    var massMix: Double
)
