package ru.kitfactory.mixingautopaint

import android.app.Application
import ru.kitfactory.mixingautopaint.di.AppComponent
import ru.kitfactory.mixingautopaint.di.AppModule
import ru.kitfactory.mixingautopaint.di.DaggerAppComponent

class App: Application() {
    companion object{
        lateinit var instance: App
    }
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent
            .builder().
            appModule(AppModule(instance))
            .build()
    }
}