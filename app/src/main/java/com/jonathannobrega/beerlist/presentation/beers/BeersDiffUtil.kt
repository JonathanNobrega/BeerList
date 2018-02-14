package com.jonathannobrega.beerlist.presentation.beers

import android.support.v7.util.DiffUtil
import com.jonathannobrega.beerlist.presentation.model.PresentationBeer

class BeersDiffUtil(private val oldBeers: List<PresentationBeer>,
                    private val newBeers: List<PresentationBeer>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldBeers.size
    }

    override fun getNewListSize(): Int {
        return newBeers.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBeers[oldItemPosition].id == newBeers[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBeers[oldItemPosition] == newBeers[newItemPosition]
    }
}
