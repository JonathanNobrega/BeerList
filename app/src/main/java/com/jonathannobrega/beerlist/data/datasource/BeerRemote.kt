package com.jonathannobrega.beerlist.data.datasource

import com.jonathannobrega.beerlist.data.model.DataBeer
import io.reactivex.Single

/**
 * Interface defining methods to access beers from a remote source. This is to be implemented by
 * the remote layer, using this interface as a way of communicating.
 */
interface BeerRemote {

    /**
     * Returns a list of [DataBeer]s from a remote source.
     *
     * @param page Current pagination's page number
     * @param perPage Amount of results with each page
     * @param searchQuery A string used to filter results based on a beer's name
     */
    fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<DataBeer>>

    /**
     * Returns a [DataBeer] with the specified id.
     *
     * @param beerId A unique beer identifier
     */
    fun getBeerById(beerId: Long): Single<DataBeer>
}