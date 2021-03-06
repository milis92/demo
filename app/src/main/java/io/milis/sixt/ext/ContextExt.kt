package io.milis.sixt.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.milis.sixt.BuildConfig

inline fun <reified T : Any> Activity.launchActivity(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    if (!this.isFinishing) {
        startActivityForResult(intent, requestCode, options)
    }
}

inline fun Activity.launchActivity(
        requestCode: Int = -1,
        options: Bundle? = null,
        className: String,
        init: Intent.() -> Unit = {}) {
    val intent = newIntent(className)
    intent.init()
    if (!this.isFinishing) {
        startActivityForResult(intent, requestCode, options)
    }
}

inline fun <reified T : Any> Context.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun Context.launchActivity(
        options: Bundle? = null,
        className: String,
        init: Intent.() -> Unit = {}) {
    val intent = newIntent(className)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

fun newIntent(clazz: String): Intent =
        Intent(Intent.ACTION_VIEW).setClassName(BuildConfig.APPLICATION_ID, clazz)