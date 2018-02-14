package com.jonathannobrega.beerlist.data.mapper

import com.jonathannobrega.beerlist.data.model.DataBeer
import com.jonathannobrega.beerlist.domain.model.Beer
import javax.inject.Inject

/**
 * Maps a [DataBeer] to and from a [Beer] when data is moving between data layer and domain layer.
 */
class DataBeerMapper @Inject constructor() {

    /**
     * Maps an instance of a [Beer] from domain layer to a [DataBeer] from data layer.
     */
    fun mapFromDomainToData(beer: Beer): DataBeer {
        return DataBeer(beer.id, beer.name, beer.tagLine, beer.description, beer.imageUrl,
                beer.isFavorite)
    }

    /**
     * Maps a list of [Beer]s from domain layer to a list of [DataBeer]s from data layer.
     */
    fun mapFromDomainToData(beers: List<Beer>): List<DataBeer> {
        return beers.map { beer ->
            DataBeer(beer.id, beer.name, beer.tagLine, beer.description, beer.imageUrl,
                    beer.isFavorite)
        }
    }

    /**
     * Maps an instance of a [DataBeer] from data layer to a [Beer] from domain layer.
     */
    fun mapFromDataToDomain(dataBeer: DataBeer): Beer {
        return Beer(dataBeer.id, dataBeer.name, dataBeer.tagLine, dataBeer.description,
                dataBeer.imageUrl, dataBeer.isFavorite)
    }

    /**
     * Maps a list of [DataBeer]s from data layer to a list of [Beer]s from domain layer.
     */
    fun mapFromDataToDomain(dataBeers: List<DataBeer>): List<Beer> {
        return dataBeers.map { dataBeer ->
            Beer(dataBeer.id, dataBeer.name, dataBeer.tagLine, dataBeer.description,
                    dataBeer.imageUrl, dataBeer.isFavorite)
        }
    }
}