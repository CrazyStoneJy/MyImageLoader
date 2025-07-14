package me.crazystone.study.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import me.crazystone.study.imageloader.cache.DiskCache
import me.crazystone.study.imageloader.cache.LruMemoryCache
import me.crazystone.study.imageloader.cache.SimpleDiskCache
import me.crazystone.study.imageloader.fetch.OkHttpFetcher
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

object ImageLoader {

    private val memoryCache = LruMemoryCache(10 * 1024) // 10MB
    private var diskCache: DiskCache? = null
    private val fetcher = OkHttpFetcher()
    private var isInit = false


    fun init(context: Context) {
        diskCache = SimpleDiskCache(File(context.cacheDir, "images"))
        isInit = true
    }

    suspend fun load(url: String): Bitmap? {

        if (!isInit) {
            throw Exception("ImageLoader is not initialize.")
        }

        memoryCache.get(url)?.let { return it }

        diskCache?.get(url)?.let {
            val bitmap = BitmapFactory.decodeFile(it.absolutePath)
            memoryCache.put(url, bitmap)
            return bitmap
        }

        return try {
            val input = fetcher.fetch(url)
            val bitmap = BitmapFactory.decodeStream(input)

            memoryCache.put(url, bitmap)
            diskCache?.put(url, bitmapToInputStream(bitmap))

            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun bitmapToInputStream(bitmap: Bitmap): InputStream {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return ByteArrayInputStream(baos.toByteArray())
    }
}