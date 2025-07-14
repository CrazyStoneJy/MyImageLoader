package me.crazystone.study.imageloader.fetch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.io.InputStream

class OkHttpFetcher : NetworkFetcher {
    private val client = OkHttpClient()

    override suspend fun fetch(url: String): InputStream = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        if (!response.isSuccessful) {
            throw IOException("Unexpected code $response")
        }

        response.body?.byteStream() ?: throw IOException("Empty body")
    }
}