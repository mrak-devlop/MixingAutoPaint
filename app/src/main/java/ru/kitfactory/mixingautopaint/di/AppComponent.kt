package ru.kitfactory.mixingautopaint.di

import dagger.Component
import ru.kitfactory.mixingautopaint.MainActivity

@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}