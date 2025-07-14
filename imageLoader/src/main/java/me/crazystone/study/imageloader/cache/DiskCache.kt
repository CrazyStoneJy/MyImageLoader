package me.crazystone.study.imageloader.cache

import java.io.File
import java.io.InputStream

interface DiskCache {
    fun get(key: String): File?
    fun put(key: String, inputStream: InputStream)
}