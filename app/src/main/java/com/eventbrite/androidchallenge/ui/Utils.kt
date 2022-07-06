package com.eventbrite.androidchallenge.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View?.showSnackbar(message: Int, actionName: Int, action: () -> Unit) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG).setAction(actionName) {
            action()
        }.show()
    }
}
