package ru.kitfactory.mixingautopaint.di

import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {
}