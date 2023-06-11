package com.bike.app.ui.util

import android.content.res.Resources

object DisplayMetricsUtil {

    fun dpToPx(dp: Float): Float {
        val density = Resources.getSystem().displayMetrics.density
        return dp * density
    }

}