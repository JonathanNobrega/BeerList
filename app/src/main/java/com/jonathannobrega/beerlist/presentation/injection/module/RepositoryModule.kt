package com.jonathannobrega.beerlist.presentation.injection.module

import com.jonathannobrega.beerlist.data.BeerDataRepository
import com.jonathannobrega.beerlist.data.datasource.BeerLocal
import com.jonathannobrega.beerlist.data.datasource.BeerRemote
import com.jonathannobrega.beerlist.data.mapper.DataBeerMapper
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @PerApplication
    fun provideBeerRepository(beerLocal: BeerLocal,
                              beerRemote: BeerRemote,
                              beerMapper: DataBeerMapper): BeerRepository {
        return BeerDataRepository(beerLocal, beerRemote, beerMapper)
    }
}