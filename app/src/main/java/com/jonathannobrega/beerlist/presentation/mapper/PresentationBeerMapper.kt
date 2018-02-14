package com.jonathannobrega.beerlist.presentation.mapper

import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.presentation.model.PresentationBeer
import javax.inject.Inject

/**
 * Maps a [PresentationBeer] to and from a [Beer] when data is moving between presentation layer
 * and domain layer.
 */
class PresentationBeerMapper @Inject constructor() {

    /**
     * Maps an instance of a [Beer] from domain layer to a [PresentationBeer] from presentation
     * layer.
     */
    fun mapFromDomainToPresentation(beer: Beer): PresentationBeer {
        return PresentationBeer(beer.id, beer.name, beer.tagLine, beer.description, beer.imageUrl,
                beer.isFavorite)
    }

    /**
     * Maps an instance of a [PresentationBeer] from presentation layer to a [Beer] from domain
     * layer.
     */
    fun mapFromPresentationToDomain(presentationBeer: PresentationBeer): Beer {
        return Beer(presentationBeer.id, presentationBeer.name, presentationBeer.tagLine,
                presentationBeer.description, presentationBeer.imageUrl, presentationBeer.isFavorite)
    }
}