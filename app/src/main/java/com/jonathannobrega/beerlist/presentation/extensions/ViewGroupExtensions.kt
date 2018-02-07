package com.jonathannobrega.beerlist.presentation.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutResourceId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context)
            .inflate(layoutResourceId, this, attachToRoot)
}