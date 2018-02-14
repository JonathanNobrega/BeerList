package com.jonathannobrega.beerlist.remote.model

import com.squareup.moshi.Json

/**
 * Representation of a Beer which comes from a remote source.
 */
data class RemoteBeer(
        @Json(name = "id") val id: Long,
        @Json(name = "name") val name: String,
        @Json(name = "tagline") val tagLine: String,
        @Json(name = "description") val description: String,
        @Json(name = "image_url") val imageUrl: String,
        val isFavorite: Boolean
)