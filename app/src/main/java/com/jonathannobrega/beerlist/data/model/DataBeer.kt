package com.jonathannobrega.beerlist.data.model

/**
 * Representation of a Beer fetched from an external layer data source.
 */
data class DataBeer(
        val id: Long,
        val name: String,
        val tagLine: String,
        val description: String,
        val imageUrl: String,
        val isFavorite: Boolean
)