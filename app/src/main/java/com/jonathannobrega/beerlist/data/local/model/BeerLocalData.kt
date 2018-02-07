package com.jonathannobrega.beerlist.data.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class BeerLocalData(
        @PrimaryKey @Required var id: String = "",
        var name: String = "",
        var status: String = "",
        var description: String = "",
        var imageUrl: String = ""
) : RealmObject()