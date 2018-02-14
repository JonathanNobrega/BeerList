package com.jonathannobrega.beerlist.data

import com.jonathannobrega.beerlist.data.datasource.BeerLocal
import com.jonathannobrega.beerlist.data.datasource.BeerRemote
import com.jonathannobrega.beerlist.data.mapper.DataBeerMapper
import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class BeerDataRepository @Inject constructor(
        private val beerLocal: BeerLocal,
        private val beerRemote: BeerRemote,
        private val beerMapper: DataBeerMapper
) : BeerRepository {

    override fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<Beer>> {
        return if (beerLocal.isCached((page * perPage) + perPage)) {
            beerLocal.getBeers(page, perPage, searchQuery)
                    .map { beerMapper.mapFromDataToDomain(it) }
        } else {
            beerRemote.getBeers(page, perPage, searchQuery)
                    .map { beerMapper.mapFromDataToDomain(it) }
                    .doOnSuccess { saveBeers(it) }
        }
    }

    override fun getBeerById(beerId: Long): Single<Beer> {
        return if (beerLocal.isBeerCached(beerId)) {
            beerLocal.getBeerById(beerId)
                    .map { beerMapper.mapFromDataToDomain(it) }
        } else {
            beerRemote.getBeerById(beerId)
                    .map { beerMapper.mapFromDataToDomain(it) }
        }
    }

    override fun saveBeers(beers: List<Beer>): Completable {
        return beerLocal.saveBeers(beerMapper.mapFromDomainToData(beers))
    }
}