package com.example.core.utils

import android.content.Context

fun Context.getBaseModuleSharedPreferences() =
    getSharedPreferences(applicationContext.packageName, Context.MODE_PRIVATE)
