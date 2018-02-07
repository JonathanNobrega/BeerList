package com.jonathannobrega.beerlist.domain.model

/**
 * Representation for a [Beer] fetched from an external layer data source
 */
data class Beer(
        val id: String,
        val name: String,
        val status: String,
        val description: String,
        val imageUrl: String
)