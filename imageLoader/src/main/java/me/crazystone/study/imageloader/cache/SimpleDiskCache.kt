package me.crazystone.study.imageloader.cache

import java.io.File
import java.io.InputStream

class SimpleDiskCache(private val directory: File) : DiskCache {

    private fun hashKey(key: String): String = key.hashCode().toString()

    private fun keyToFile(key: String): File {
        return File(directory, key.hashCode().toString())
    }

    override fun get(key: String): File? {
        val file = keyToFile(key)
        return if (file.exists()) file else null
    }

    override fun put(key: String, inputStream: InputStream) {
        val file = keyToFile(key)

        // ✅ 确保缓存目录存在
        file.parentFile?.mkdirs()

        file.outputStream().use { output ->
            inputStream.copyTo(output)
        }
    }

}