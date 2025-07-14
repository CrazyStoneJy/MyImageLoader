package me.crazystone.study.imageloader.cache

import android.graphics.Bitmap
import android.util.LruCache

class LruMemoryCache(maxSize: Int) : MemoryCache {
    private val cache = object : LruCache<String, Bitmap>(maxSize) {
        override fun sizeOf(key: String, value: Bitmap): Int {
            return value.byteCount / 1024
        }
    }

    override fun get(key: String): Bitmap? = cache.get(key)

    override fun put(key: String, bitmap: Bitmap) {
        cache.put(key, bitmap)
    }
}