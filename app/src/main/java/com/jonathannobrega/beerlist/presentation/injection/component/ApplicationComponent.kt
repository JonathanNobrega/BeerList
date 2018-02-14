package com.jonathannobrega.beerlist.presentation.injection.component

import com.jonathannobrega.beerlist.presentation.BeerListApplication
import com.jonathannobrega.beerlist.presentation.injection.module.ApplicationModule
import com.jonathannobrega.beerlist.presentation.injection.module.LocalModule
import com.jonathannobrega.beerlist.presentation.injection.module.RemoteModule
import com.jonathannobrega.beerlist.presentation.injection.module.RepositoryModule
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Component

@PerApplication
@Component(
        modules = arrayOf(ApplicationModule::class,
                RepositoryModule::class,
                LocalModule::class,
                RemoteModule::class)
)
interface ApplicationComponent {

    fun inject(application: BeerListApplication)
}