package ru.kitfactory.mixingautopaint.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.kitfactory.domain.PaintRepository
import ru.kitfactory.domain.models.PaintDomainModel
import ru.kitfactory.mixingautopaint.data.db.DbDao
import ru.kitfactory.mixingautopaint.data.db.PaintDbModel
import ru.kitfactory.mixingautopaint.data.db.asDomainModel
import javax.inject.Inject

class PaintRepositoryImpl @Inject constructor (private val dbDao: DbDao):PaintRepository {
    override fun getPaints(): Flow<List<PaintDomainModel>> {
        return dbDao.getPaints().map { it.asDomainModel() }
    }

    override suspend fun addNewPaint(paint: PaintDomainModel) {
            dbDao.addPaint(convertPaintModelToPaint(paint))
    }

    override suspend fun removePaint(id: Int) {
        withContext(Dispatchers.IO) {
            val gPaint = dbDao.getPaint(id)
            gPaint.collect { dbDao.removePaint(it) }
        }

    }

    override suspend fun updatePaint(paint: PaintDomainModel) {
        dbDao.updatePaint(convertPaintModelToPaint(paint))
    }

    private fun convertPaintModelToPaint(paintDomainModel: PaintDomainModel) : PaintDbModel {
       return PaintDbModel(
            id = paintDomainModel.id,
            titleMix = paintDomainModel.titleMix,
            partPaint = paintDomainModel.partPaint,
            partHardener = paintDomainModel.partHardener,
            partDiluent = paintDomainModel.partDiluent,
            paintMass = paintDomainModel.paintMass,
            paintMassForMix = paintDomainModel.paintMassForMix,
            massHardenerForMix = paintDomainModel.massHardenerForMix,
            paintPlusHardener = paintDomainModel.paintPlusHardener,
            massDiluentForMix = paintDomainModel.massDiluentForMix

        )
    }

}