package com.jonathannobrega.beerlist.presentation.injection.module

import com.jonathannobrega.beerlist.data.datasource.BeerLocal
import com.jonathannobrega.beerlist.local.datasource.LocalBeerDataSource
import com.jonathannobrega.beerlist.local.mapper.LocalBeerMapper
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    @PerApplication
    fun provideBeerLocal(localBeerMapper: LocalBeerMapper): BeerLocal {
        return LocalBeerDataSource(localBeerMapper)
    }
}