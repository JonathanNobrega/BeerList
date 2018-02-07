package com.jonathannobrega.beerlist.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bluelinelabs.conductor.Controller

abstract class BaseController : Controller {

    constructor() : super()

    constructor(args: Bundle) : super(args)

    override fun onDetach(view: View) {
        super.onDetach(view)
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
