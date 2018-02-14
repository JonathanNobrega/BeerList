package com.jonathannobrega.beerlist.presentation.injection.component

import com.jonathannobrega.beerlist.presentation.beers.BeersController
import com.jonathannobrega.beerlist.presentation.injection.module.BeersControllerModule
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Component

// TODO: Fix scopes
@PerApplication
@Component(modules = [(BeersControllerModule::class)])
interface BeersComponent {

    fun inject(beersController: BeersController)
}