package me.crazystone.study.imageloader

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

fun ImageView.loadImage(
    url: String,
    placeholder: Drawable? = null,
    onError: (() -> Unit)? = null
) {
    if (placeholder != null) {
        setImageDrawable(placeholder)
    }

    val lifecycleOwner = context.findLifecycleOwner() ?: return

    lifecycleOwner.lifecycleScope.launch {
        val bitmap = ImageLoader.load(url)
        if (bitmap != null) {
            this@loadImage.setImageBitmap(bitmap)
        } else {
            onError?.invoke()
        }
    }
}