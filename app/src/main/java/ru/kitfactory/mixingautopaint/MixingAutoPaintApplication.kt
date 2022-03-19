package ru.kitfactory.mixingautopaint

import android.app.Application
import ru.kitfactory.mixingautopaint.data.storage.DbSource

class MixingAutoPaintApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DbSource.initialize(this)
    }
}