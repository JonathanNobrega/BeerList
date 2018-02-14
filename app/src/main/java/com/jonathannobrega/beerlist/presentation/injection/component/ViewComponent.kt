package com.jonathannobrega.beerlist.presentation.injection.component

import com.jonathannobrega.beerlist.presentation.beerdetails.BeerDetailsController
import com.jonathannobrega.beerlist.presentation.beers.BeersController
import com.jonathannobrega.beerlist.presentation.injection.module.*
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Component

// TODO: Fix scopes
@PerApplication
@Component(modules = [(BeersControllerModule::class)])
interface ViewComponent {

    fun inject(beersController: BeersController)

    fun inject(beerDetailsController: BeerDetailsController)
}