package com.example.sampleapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment

fun String.removeRegex(): String {
    val match = Regex("(.*)(<img(.*?)/>)(.*)").find(this)
    return this.replace(match?.groupValues?.getOrNull(2) ?: "", "")
}

fun Fragment.openBrowser(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.goneIf(shouldGone: Boolean) {
    this.visibility = if (shouldGone) View.GONE else View.VISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}