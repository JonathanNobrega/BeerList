package com.jonathannobrega.beerlist.presentation.injection.module

import com.jonathannobrega.beerlist.presentation.beers.BeersContract
import com.jonathannobrega.beerlist.presentation.beers.BeersPresenter
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ApplicationModule::class,
        RepositoryModule::class,
        LocalModule::class,
        RemoteModule::class))
class BeersControllerModule {

    @Provides
    fun provideBeersPresenter(beersPresenter: BeersPresenter): BeersContract.Presenter {
        return beersPresenter
    }
}