package ru.kitfactory.mixingautopaint.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.data.storage.db.DbDao
import ru.kitfactory.mixingautopaint.data.storage.db.LocalDatabase

@Module
class DataModule(val context: Context) {

    @Provides
    fun provideLocalDatabase(context: Context): DbDao {
         return LocalDatabase.getDatabase(context).dbDao()
    }

    @Provides
    fun provideRepository(dbDao: DbDao): LocalRepository {
        return LocalRepository (dbDao)
    }
}