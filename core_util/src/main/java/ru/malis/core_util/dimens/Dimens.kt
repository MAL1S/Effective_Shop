package ru.malis.core_util.dimens

import android.content.Context


fun Int.dp(context: Context): Int {
    return (context.resources.displayMetrics.density * this + 0.5f).toInt()
}