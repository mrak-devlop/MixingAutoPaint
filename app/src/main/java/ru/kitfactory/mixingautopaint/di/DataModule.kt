package ru.kitfactory.mixingautopaint.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.kitfactory.domain.PaintRepository
import ru.kitfactory.mixingautopaint.data.db.DbDao

import ru.kitfactory.mixingautopaint.data.repository.PaintRepositoryImpl

import ru.kitfactory.mixingautopaint.data.db.LocalDatabase


import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideLocalDatabase(application: Application): DbDao {
         return LocalDatabase.getDatabase(application).dbDao()
    }
    @Singleton
    @Provides
    fun providePaintRepositoryImpl(dbDao: DbDao): PaintRepository{
        return PaintRepositoryImpl(dbDao)
    }
}