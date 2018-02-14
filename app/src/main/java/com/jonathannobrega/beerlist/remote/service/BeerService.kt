package com.jonathannobrega.beerlist.remote.service

import com.jonathannobrega.beerlist.remote.model.RemoteBeer
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Maps some endpoints for interaction with the PunkAPI using Retrofit.
 */
interface BeerService {

    /**
     * Returns a list of [RemoteBeer]s.
     *
     * @param page Current pagination's page number
     * @param perPage Amount of results with each page
     * @param searchQuery A string used to filter results based on a beer's name
     */
    @GET("beers")
    fun getBeers(@Query("page") page: Int,
                 @Query("per_page") perPage: Int,
                 @Query("beer_name") searchQuery: String?): Single<List<RemoteBeer>>

    /**
     * Returns a [RemoteBeer] with the specified id.
     *
     * @param beerId Beer unique identifier
     */
    @GET("beers/{id}")
    fun getBeerById(@Path("id") beerId: Long): Single<RemoteBeer>
}