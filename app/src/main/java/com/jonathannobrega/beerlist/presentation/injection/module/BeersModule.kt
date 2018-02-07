package com.jonathannobrega.beerlist.presentation.injection.module

import com.jonathannobrega.beerlist.data.network.datasource.NetworkBeerDataSource
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import com.jonathannobrega.beerlist.presentation.beers.BeersContract
import com.jonathannobrega.beerlist.presentation.beers.BeersController
import com.jonathannobrega.beerlist.presentation.beers.BeersPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class BeersModule {

//    @Provides
//    fun provideBeerService(retrofit: Retrofit): BeerService =
//            retrofit.create(BeerService::class.java)

    @Provides
    fun provideBeerRepository(networkBeerDataSource: NetworkBeerDataSource): BeerRepository {
        // TODO: Finish implementing data layer
        return networkBeerDataSource
    }

    @Provides
    fun provideBeersPresenter(beersPresenter: BeersPresenter): BeersContract.Presenter {
        return beersPresenter
    }

    @Provides
    fun provideBeersView(beersController: BeersController): BeersContract.View {
        return beersController
    }
}
