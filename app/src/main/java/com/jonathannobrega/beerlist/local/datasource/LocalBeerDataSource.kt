package com.jonathannobrega.beerlist.local.datasource

import com.jonathannobrega.beerlist.data.datasource.BeerLocal
import com.jonathannobrega.beerlist.data.model.DataBeer
import com.jonathannobrega.beerlist.local.mapper.LocalBeerMapper
import com.jonathannobrega.beerlist.local.model.LocalBeer
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import javax.inject.Inject

/**
 * Local implementation for retrieving beers using a [Realm] database. This class is a concrete
 * implementation of [BeerLocal] interface from the data layer.
 */
class LocalBeerDataSource @Inject constructor(
        private val realm: Realm,
        private val localBeerMapper: LocalBeerMapper
) : BeerLocal {

    override fun clearBeers(): Completable {
        return Completable.defer {
            realm.delete(LocalBeer::class.java)
            Completable.complete()
        }
    }

    override fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<DataBeer>> {
        val startIndex = perPage * page
        val endIndex = startIndex + perPage

        val beers = realm.copyFromRealm(
                realm.where(LocalBeer::class.java)
                        .like("name", "*$searchQuery*")
                        .findAll())

        val beersSubList = if (beers.size >= endIndex) {
            beers.subList(startIndex, endIndex)
        } else {
            beers.subList(startIndex, beers.size)
        }

        return Single.just(beersSubList.map { localBeerMapper.mapFromLocalToData(it) })
    }

    override fun getBeerById(beerId: Long): Single<DataBeer> {
        val realmBeer = realm.where(LocalBeer::class.java)
                .equalTo("id", beerId)
                .findFirst() ?: throw IllegalStateException("Beer with id $beerId does not exist")

        val beer = realm.copyFromRealm(realmBeer)

        return Single.just(localBeerMapper.mapFromLocalToData(beer))
    }

    override fun saveBeers(dataBeers: List<DataBeer>): Completable {
        return Completable.defer {
            realm.executeTransaction {
                it.copyToRealmOrUpdate(localBeerMapper.mapFromDataToLocal(dataBeers))
            }
            Completable.complete()
        }
    }

    override fun isBeerCached(beerId: Long): Boolean {
        return realm.where(LocalBeer::class.java)
                .equalTo("id", beerId)
                .findFirst() != null
    }

    override fun isCached(beerQuantity: Int): Boolean {
        return realm.where(LocalBeer::class.java)
                .count() >= beerQuantity
    }
}