package com.jonathannobrega.beerlist.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Representation of a Beer which comes from a local database.
 */
open class LocalBeer(
        @PrimaryKey var id: Long = 0,
        var name: String = "",
        var tagLine: String = "",
        var description: String = "",
        var imageUrl: String = "",
        var isFavorite: Boolean = false
) : RealmObject()