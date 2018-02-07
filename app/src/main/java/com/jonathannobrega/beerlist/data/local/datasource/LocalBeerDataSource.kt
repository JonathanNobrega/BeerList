package com.jonathannobrega.beerlist.data.local.datasource

import com.jonathannobrega.beerlist.data.local.model.BeerLocalData
import io.reactivex.Single

interface LocalBeerDataSource {

    fun getBeers(): Single<List<BeerLocalData>>

    fun isCached(): Boolean
}