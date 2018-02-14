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
        private val localBeerMapper: LocalBeerMapper
) : BeerLocal {

    override fun clearBeers(): Completable {
        return Completable.defer {
            val realm = Realm.getDefaultInstance()
            realm.delete(LocalBeer::class.java)
            realm.close()
            Completable.complete()
        }
    }

    override fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<DataBeer>> {
        val realm = Realm.getDefaultInstance()
        val startIndex = perPage * (page - 1)
        val endIndex = startIndex + perPage

        val query = realm.where(LocalBeer::class.java)

        if (searchQuery != null) {
            query.like("name", "*$searchQuery*")
        }

        val beers = realm.copyFromRealm(query.findAll())

        val beersSubList = if (beers.size >= startIndex && endIndex < beers.size) {
            beers.subList(startIndex, endIndex)
        } else if (beers.size >= startIndex) {
            beers.subList(startIndex, beers.size)
        } else {
            listOf<LocalBeer>()
        }

        realm.close()

        return Single.just(beersSubList.map { localBeerMapper.mapFromLocalToData(it) })
    }

    override fun getBeerById(beerId: Long): Single<DataBeer> {
        val realm = Realm.getDefaultInstance()
        val realmBeer = realm.where(LocalBeer::class.java)
                .equalTo("id", beerId)
                .findFirst() ?: throw IllegalStateException("Beer with id $beerId does not exist")

        val beer = realm.copyFromRealm(realmBeer)
        realm.close()

        return Single.just(localBeerMapper.mapFromLocalToData(beer))
    }

    override fun saveBeers(dataBeers: List<DataBeer>): Completable {
        val realm = Realm.getDefaultInstance()

        return Completable.defer {
            realm.executeTransaction {
                it.copyToRealmOrUpdate(localBeerMapper.mapFromDataToLocal(dataBeers))
            }
            realm.close()
            Completable.complete()
        }
    }

    override fun isBeerCached(beerId: Long): Boolean {
        val realm = Realm.getDefaultInstance()
        val isCached = realm.where(LocalBeer::class.java)
                .equalTo("id", beerId)
                .findFirst() != null
        realm.close()

        return isCached
    }

    override fun isCached(beerQuantity: Int): Boolean {
        val realm = Realm.getDefaultInstance()
        val isCached = realm.where(LocalBeer::class.java)
                .count() >= beerQuantity
        realm.close()

        return isCached
    }
}