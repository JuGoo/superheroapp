package com.ultimate.superheroapp.utils.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun process(imageUrl: String, imageView: ImageView)
    fun clearLoadings(imageView: ImageView)
}