package me.crazystone.study.imageloader

import android.app.Application

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(this)
    }

}