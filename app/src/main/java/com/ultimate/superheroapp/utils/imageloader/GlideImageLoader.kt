package com.ultimate.superheroapp.utils.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

internal class GlideImageLoader : ImageLoader {

    override fun process(imageUrl: String, imageView: ImageView) {

        val requestManager = Glide.with(imageView.context)
        val requestBuilder = requestManager.load(imageUrl)
        requestBuilder
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .into(imageView)
    }

    override fun clearLoadings(imageView: ImageView) {
        Glide.with(imageView.context).clear(imageView)
    }
}