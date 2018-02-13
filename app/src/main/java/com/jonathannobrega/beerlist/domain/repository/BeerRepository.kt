package com.jonathannobrega.beerlist.domain.repository

import com.jonathannobrega.beerlist.domain.model.Beer
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented.
 */
interface BeerRepository {

    fun getBeers(page: Int, perPage: Int, searchQuery: String?): Single<List<Beer>>

//    fun getFavoriteBeers(): Single<List<Beer>>
//
//    fun setBeerAsFavorite(beer: Beer): Completable
}