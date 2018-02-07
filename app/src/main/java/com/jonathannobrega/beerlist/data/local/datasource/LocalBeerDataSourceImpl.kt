package com.jonathannobrega.beerlist.data.local.datasource

import com.jonathannobrega.beerlist.data.local.model.BeerLocalData
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

class LocalBeerDataSourceImpl @Inject constructor(private val realm: Realm) : LocalBeerDataSource {

    override fun getBeers(): Single<List<BeerLocalData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}