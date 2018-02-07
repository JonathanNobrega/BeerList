package com.jonathannobrega.beerlist.presentation.injection.component

import com.jonathannobrega.beerlist.presentation.beers.BeersController
import com.jonathannobrega.beerlist.presentation.injection.module.BeersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(BeersModule::class)])
interface BeersComponent {

    fun inject(beersController: BeersController)
}
