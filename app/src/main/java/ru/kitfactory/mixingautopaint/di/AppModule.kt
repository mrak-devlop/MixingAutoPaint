package ru.kitfactory.mixingautopaint.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(application: Application) {
    private val mainApplication = application

    @Provides
    fun provideApplication(): Application{
        return mainApplication
    }
}