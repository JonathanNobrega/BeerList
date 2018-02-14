package com.jonathannobrega.beerlist.domain.repository

import com.jonathannobrega.beerlist.domain.model.Beer
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented.
 */
interface BeerRepository {

    /**
     * Returns a list of [Beer]s.
     *
     * @param page Current pagination's page number
     * @param perPage Amount of results with each page
     * @param searchQuery A string used to filter results based on a [Beer]'s name
     */
    fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<Beer>>

    /**
     * Returns a [Beer] with the specified id.
     *
     * @param beerId A unique [Beer] identifier
     */
    fun getBeerById(beerId: Long): Single<Beer>

    /**
     * Saves a given list of [Beer]s.
     */
    fun saveBeers(beers: List<Beer>): Completable
}