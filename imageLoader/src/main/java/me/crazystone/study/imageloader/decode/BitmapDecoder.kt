package me.crazystone.study.imageloader.decode

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream

object BitmapDecoder {
    fun decode(inputStream: InputStream): Bitmap =
        BitmapFactory.decodeStream(inputStream)
}