package com.jonathannobrega.beerlist.presentation.model

/**
 * Representation of a Beer used on presentation layer.
 */
data class PresentationBeer(
        val id: Long,
        val name: String,
        val tagLine: String,
        val description: String,
        val imageUrl: String,
        val isFavorite: Boolean
)