package com.jonathannobrega.beerlist.presentation.model

/**
 * Representation for a [BeerViewModel] fetched from an external layer data source
 */
data class BeerViewModel( // PresentationBeer
        val id: String,
        val name: String,
        val status: String,
        val description: String,
        val imageUrl: String
)