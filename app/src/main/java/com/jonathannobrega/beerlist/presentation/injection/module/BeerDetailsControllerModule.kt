package com.jonathannobrega.beerlist.presentation.injection.module

import com.jonathannobrega.beerlist.domain.interactor.beer.GetBeerByIdUseCase
import com.jonathannobrega.beerlist.presentation.beerdetails.BeerDetailsContract
import com.jonathannobrega.beerlist.presentation.beerdetails.BeerDetailsPresenter
import com.jonathannobrega.beerlist.presentation.mapper.PresentationBeerMapper
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ApplicationModule::class,
        RepositoryModule::class,
        LocalModule::class,
        RemoteModule::class))
class BeerDetailsControllerModule(private val beerId: Long) {

    @Provides
    fun provideBeerDetailsPresenter(getBeerByIdUseCase: GetBeerByIdUseCase,
                                    beerMapper: PresentationBeerMapper): BeerDetailsContract.Presenter {
        return BeerDetailsPresenter(beerId, getBeerByIdUseCase, beerMapper)
    }
}