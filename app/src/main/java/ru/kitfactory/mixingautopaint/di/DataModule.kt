package ru.kitfactory.mixingautopaint.di


import android.app.Application
import dagger.Module
import dagger.Provides
import ru.kitfactory.mixingautopaint.data.repository.LocalRepositoryImpl
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase

@Module
class DataModule {
    @Provides
    fun provideDbDao(application: Application): DbDao {
        return LocalDatabase.getDatabase(application).dbDao()
    }

    @Provides
    fun provideRepository(dbDao: DbDao): LocalRepositoryImpl {
        return LocalRepositoryImpl(dbDao)
    }

}