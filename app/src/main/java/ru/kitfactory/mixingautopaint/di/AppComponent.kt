package ru.kitfactory.mixingautopaint.di

import dagger.Component
import ru.kitfactory.mixingautopaint.presentation.addFragment.AddMixPaintFragment
import ru.kitfactory.mixingautopaint.presentation.editFragment.EditFragment
import ru.kitfactory.mixingautopaint.presentation.listFragment.PaintListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {
    fun inject(paintListFragment: PaintListFragment)
    fun inject(addMixPaintFragment: AddMixPaintFragment)
    fun inject(editFragment: EditFragment)
}