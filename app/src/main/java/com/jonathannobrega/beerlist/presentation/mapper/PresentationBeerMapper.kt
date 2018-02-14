package com.jonathannobrega.beerlist.presentation.mapper

import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.presentation.model.PresentationBeer
import javax.inject.Inject

open class BeerMapper @Inject constructor() : Mapper<PresentationBeer, Beer> {

    /**
     * Maps a [Beer] to a [PresentationBeer] when data is moving between this layer and the Domain layer
     */
    override fun mapFromDomainToViewModel(type: Beer): PresentationBeer {
        return PresentationBeer(type.id, type.name, type.status, type.description, type.imageUrl)
    }
}