package ru.kitfactory.mixingautopaint.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.Module
import dagger.Provides
import ru.kitfactory.mixingautopaint.data.repository.LocalRepository
import ru.kitfactory.mixingautopaint.presentation.listFragment.PaintListViewModel

@Module
class AppModule(val application: Application) {
    @Provides
    fun provideApplication(): Application{
        return application
    }

    fun providePaintListViewModel(application: Application,
                                  repository: LocalRepository): AndroidViewModel{
        return PaintListViewModel(application = application, repository = repository)
    }

}