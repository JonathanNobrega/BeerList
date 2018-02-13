package com.jonathannobrega.beerlist.domain.model

/**
 * Representation of a [Beer]
 */
data class Beer(
        val id: Long,
        val name: String,
        val tagLine: String,
        val description: String,
        val imageUrl: String,
        val isFavorite: Boolean
)