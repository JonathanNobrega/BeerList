package com.jonathannobrega.beerlist.local.mapper

import com.jonathannobrega.beerlist.data.model.DataBeer
import com.jonathannobrega.beerlist.local.model.LocalBeer
import javax.inject.Inject

/**
 * Maps a [LocalBeer] to and from a [DataBeer] when data is moving between local layer and data
 * layer.
 */
class LocalBeerMapper @Inject constructor() {

    /**
     * Maps an instance of a [DataBeer] from data layer to a [LocalBeer] from local layer.
     */
    fun mapFromDataToLocal(dataBeer: DataBeer): LocalBeer {
        return LocalBeer(dataBeer.id, dataBeer.name, dataBeer.tagLine, dataBeer.description,
                dataBeer.imageUrl, dataBeer.isFavorite)
    }

    /**
     * Maps a list of [DataBeer]s from data layer to a list of [LocalBeer]s from local layer.
     */
    fun mapFromDataToLocal(dataBeers: List<DataBeer>): List<LocalBeer> {
        return dataBeers.map { dataBeer ->
            LocalBeer(dataBeer.id, dataBeer.name, dataBeer.tagLine, dataBeer.description,
                    dataBeer.imageUrl, dataBeer.isFavorite)
        }
    }

    /**
     * Maps an instance of a [LocalBeer] from local layer to a [DataBeer] from data layer.
     */
    fun mapFromLocalToData(localBeer: LocalBeer): DataBeer {
        return DataBeer(localBeer.id, localBeer.name, localBeer.tagLine, localBeer.description,
                localBeer.imageUrl, localBeer.isFavorite)
    }

    /**
     * Maps a list of [LocalBeer]s from local layer to a list of [DataBeer]s from data layer.
     */
    fun mapFromLocalToData(localBeers: List<LocalBeer>): List<DataBeer> {
        return localBeers.map { localBeer ->
            DataBeer(localBeer.id, localBeer.name, localBeer.tagLine, localBeer.description,
                    localBeer.imageUrl, localBeer.isFavorite)
        }
    }
}