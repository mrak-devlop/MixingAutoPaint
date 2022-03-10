package ru.kitfactory.mixingautopaint.data.repository

import ru.kitfactory.mixingautopaint.data.storage.DbSource
import ru.kitfactory.mixingautopaint.domain.repository.PaintRepository
import java.util.*

class PaintRepositoryImpl: PaintRepository {
    val dbSource = DbSource
    override fun getPaint(id: UUID) {
        TODO()
    }

    override fun getPaints() {
        TODO("Not yet implemented")
    }
}