package com.jonathannobrega.beerlist.remote.datasource

import com.jonathannobrega.beerlist.data.model.DataBeer
import com.jonathannobrega.beerlist.data.datasource.BeerRemote
import com.jonathannobrega.beerlist.remote.mapper.RemoteBeerMapper
import com.jonathannobrega.beerlist.remote.service.BeerService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote implementation for retrieving beers. This class is a concrete implementation of
 * [BeerRemote] interface from the data layer.
 */
class RemoteBeerDataSource @Inject constructor(
        private val beerService: BeerService,
        private val remoteBeerMapper: RemoteBeerMapper
) : BeerRemote {

    override fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<DataBeer>> {
        return beerService.getBeers(page, perPage, searchQuery)
                .map { remoteBeers ->
                    remoteBeers.map { remoteBeer ->
                        remoteBeerMapper.mapFromRemoteToData(remoteBeer)
                    }
                }
    }

    override fun getBeerById(beerId: Long): Single<DataBeer> {
        return beerService.getBeerById(beerId)
                .map { remoteBeerMapper.mapFromRemoteToData(it) }
    }
}