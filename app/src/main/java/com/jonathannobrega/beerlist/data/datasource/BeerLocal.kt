package com.jonathannobrega.beerlist.data.datasource

import com.jonathannobrega.beerlist.data.model.DataBeer
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Beers. This is to be implemented by the
 * local layer, using this interface as a way of communicating.
 */
interface BeerLocal {

    /**
     * Clears all Beers from cache.
     */
    fun clearBeers(): Completable

    /**
     * Returns a list of [DataBeer]s from cache.
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

    /**
     * Saves a given list of beers to the cache.
     */
    fun saveBeers(dataBeers: List<DataBeer>): Completable

    /**
     * Checks if a beer exists in the cache.
     *
     * @param beerId A unique beer identifier
     *
     * @return true if the specified beer exists in cache, false otherwise
     */
    fun isBeerCached(beerId: Long): Boolean

    /**
     * TODO: Improve cache strategy
     *
     * Checks if a specified number of beers exists in cache.
     *
     * @param beerQuantity The number of beers expected to be saved
     *
     * @return true if cache has the specified number of beers saved in cache, false otherwise
     */
    fun isCached(beerQuantity: Int): Boolean
}