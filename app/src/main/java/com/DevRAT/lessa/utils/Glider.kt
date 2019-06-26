package com.sovize.ultracop.controlers.network


import android.widget.ImageView
import com.DevRAT.lessa.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import pl.droidsonroids.gif.GifImageView


object Glider {

    private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

    fun load(
        address: String,
        v: ImageView,
        placeholder: Int = R.drawable.loadinloadin,
        errorHolder: Int = R.drawable.gif_error
    ) {
        Glide.with(v.context)
            .load(address)
            //.timeout(3000)
            .fitCenter()
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            .placeholder(placeholder)
            //.thumbnail(Glide.with(v.context).load(placeholder))
            .error(errorHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(v)
    }

    fun loadCircle(
        address: String,
        v: ImageView,
        placeholder: Int = R.drawable.gif_cargando,
        errorHolder: Int = R.drawable.gif_error
    ) {
        Glide.with(v.context)
            .load(address)
            .fitCenter()
            .transition(DrawableTransitionOptions.withCrossFade(factory))
            //.placeholder(placeholder)
            .error(errorHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(v)
    }

}