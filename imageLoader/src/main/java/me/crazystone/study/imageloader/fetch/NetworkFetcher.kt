package me.crazystone.study.imageloader.fetch

import java.io.InputStream

interface NetworkFetcher {
    suspend fun fetch(url: String): InputStream
}