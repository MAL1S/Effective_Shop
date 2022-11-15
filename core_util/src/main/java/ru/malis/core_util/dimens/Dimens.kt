package ru.malis.core_util.dimens

import android.content.Context
import android.util.DisplayMetrics


fun Int.toDp(context: Context): Int {
    return (1.0f * this /
            (1.0f * context.resources.displayMetrics.densityDpi
                    / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}