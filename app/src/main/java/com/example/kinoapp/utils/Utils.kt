package com.example.kinoapp.utils

import android.content.Context

fun Context.getBaseModuleSharedPreferences() =
    getSharedPreferences(applicationContext.packageName, Context.MODE_PRIVATE)
