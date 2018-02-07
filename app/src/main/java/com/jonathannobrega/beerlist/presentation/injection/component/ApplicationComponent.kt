package com.jonathannobrega.beerlist.presentation.injection.component

import com.jonathannobrega.beerlist.presentation.BeerListApplication
import com.jonathannobrega.beerlist.presentation.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(beerListApplication: BeerListApplication)
}
