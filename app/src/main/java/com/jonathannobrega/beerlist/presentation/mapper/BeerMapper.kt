package com.jonathannobrega.beerlist.presentation.mapper

import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.presentation.model.BeerViewModel
import javax.inject.Inject

open class BeerMapper @Inject constructor() : Mapper<BeerViewModel, Beer> {

    /**
     * Maps a [Beer] to a [BeerViewModel] when data is moving between this layer and the Domain layer
     */
    override fun mapFromDomainToViewModel(type: Beer): BeerViewModel {
        return BeerViewModel(type.id, type.name, type.status, type.description, type.imageUrl)
    }
}