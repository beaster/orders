package me.sample.orders.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


object AndroidUtils {

    fun openUrl(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (browserIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(browserIntent)
        }
    }
}