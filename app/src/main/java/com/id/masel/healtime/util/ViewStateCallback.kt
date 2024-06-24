package com.id.masel.healtime.util

import android.view.View

interface ViewStateCallback<T> {
    fun onSuccess(data: T)
    fun onLoading()
    fun onError(error: String?)

    val invisible: Int
        get() = View.INVISIBLE

    val visible: Int
        get() = View.VISIBLE
}