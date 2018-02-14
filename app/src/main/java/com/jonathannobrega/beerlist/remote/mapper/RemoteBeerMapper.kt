package com.jonathannobrega.beerlist.remote.mapper

import com.jonathannobrega.beerlist.data.model.DataBeer
import com.jonathannobrega.beerlist.remote.model.RemoteBeer
import javax.inject.Inject

/**
 * Maps a [RemoteBeer] to and from a [DataBeer] when data is moving between remote layer and data
 * layer.
 */
class RemoteBeerMapper @Inject constructor() {

    /**
     * Maps an instance of a [DataBeer] from data layer to a [RemoteBeer] from remote layer.
     */
    fun mapFromDataToRemote(dataBeer: DataBeer): RemoteBeer {
        return RemoteBeer(dataBeer.id, dataBeer.name, dataBeer.tagLine, dataBeer.description,
                dataBeer.imageUrl, dataBeer.isFavorite)
    }

    /**
     * Maps an instance of a [RemoteBeer] from remote layer to a [DataBeer] from data layer.
     */
    fun mapFromRemoteToData(remoteBeer: RemoteBeer): DataBeer {
        return DataBeer(remoteBeer.id, remoteBeer.name, remoteBeer.tagLine, remoteBeer.description,
                remoteBeer.imageUrl, remoteBeer.isFavorite)
    }
}