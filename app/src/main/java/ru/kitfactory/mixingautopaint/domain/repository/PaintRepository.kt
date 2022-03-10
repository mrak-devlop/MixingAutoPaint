package ru.kitfactory.mixingautopaint.domain.repository

import java.util.*

interface PaintRepository {
    fun getPaints()
    fun getPaint(id: UUID)
}