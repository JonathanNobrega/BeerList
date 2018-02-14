package com.jonathannobrega.beerlist.presentation.beers

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jonathannobrega.beerlist.R
import com.jonathannobrega.beerlist.presentation.extensions.inflate
import com.jonathannobrega.beerlist.presentation.model.PresentationBeer
import kotlinx.android.synthetic.main.item_beer.view.*

class BeersAdapter(
        val itemListener: ItemListener
) : RecyclerView.Adapter<BeersAdapter.BeersViewHolder>() {

    private val beers: MutableList<PresentationBeer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        val view = parent.inflate(R.layout.item_beer)
        return BeersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        val beer = beers[position]
        holder.view.textViewBeerName.text = beer.name
        holder.view.textViewBeerStatus.text = beer.tagLine

        Glide.with(holder.view.context)
                .load(beer.imageUrl)
                .apply(
                        RequestOptions.circleCropTransform()
                                .placeholder(R.color.placeholder_color)
                )
                .into(holder.view.imageViewBeerImage)
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    fun getData(): List<PresentationBeer> {
        return beers
    }

    fun refreshData(beers: List<PresentationBeer>) {
        val diffResult = DiffUtil.calculateDiff(BeersDiffUtil(this.beers, beers))
        this.beers.clear()
        this.beers.addAll(beers)
        diffResult.dispatchUpdatesTo(this)
    }

    /********** Inner classes **********/

    inner class BeersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val beer = beers[adapterPosition]
                    itemListener.onBeerClicked(beer)
                }
            }
        }
    }

    /********** Interfaces **********/

    interface ItemListener {

        fun onBeerClicked(beer: PresentationBeer)
    }
}
