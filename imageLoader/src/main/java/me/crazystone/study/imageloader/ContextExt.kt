package me.crazystone.study.imageloader

import android.content.Context
import android.content.ContextWrapper
import androidx.lifecycle.LifecycleOwner

fun Context.findLifecycleOwner(): LifecycleOwner? {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is LifecycleOwner) return ctx
        ctx = ctx.baseContext
    }
    return null
}