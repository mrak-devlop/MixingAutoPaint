package ru.kitfactory.mixingautopaint.app

import android.app.Application
import ru.kitfactory.mixingautopaint.di.AppComponent
import ru.kitfactory.mixingautopaint.di.AppModule
import ru.kitfactory.mixingautopaint.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(application = this))
            .build()
    }
}