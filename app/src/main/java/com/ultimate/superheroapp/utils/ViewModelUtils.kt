package com.ultimate.superheroapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.onChange(
    lifecycleOwner: LifecycleOwner,
    callback: (T) -> Unit
) {
    observe(lifecycleOwner, callback)
}
