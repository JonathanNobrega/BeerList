package com.jonathannobrega.beerlist.presentation.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.view.View
import com.bluelinelabs.conductor.Controller

fun Controller.getViewOrThrow(): View {
    return view ?: throw IllegalStateException("View not attached to controller yet. Make sure " +
            "to call this method after onCreateView() returns")
}

fun Controller.getActivityOrThrow(): Activity =
        activity ?: throw IllegalStateException("Controller not attached to Activity yet")

fun Controller.getApplicationContextOrThrow(): Context {
    return applicationContext ?: throw IllegalStateException("Controller not attached to " +
            "Activity yet")
}

fun Controller.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(getApplicationContextOrThrow(), permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Controller.isPermissionPermanentlyDenied(permission: String): Boolean =
        !hasPermission(permission) && !shouldShowRequestPermissionRationale(permission)
