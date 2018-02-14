package com.jonathannobrega.beerlist.presentation.injection.component

import com.jonathannobrega.beerlist.presentation.beerdetails.BeerDetailsController
import com.jonathannobrega.beerlist.presentation.injection.module.BeerDetailsControllerModule
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Component

// TODO: Fix scopes
@PerApplication
@Component(modules = [(BeerDetailsControllerModule::class)])
interface BeerDetailsComponent {

    fun inject(beerDetailsController: BeerDetailsController)
}