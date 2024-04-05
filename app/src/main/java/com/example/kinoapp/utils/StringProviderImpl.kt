package com.example.kinoapp.utils

import android.content.Context
import javax.inject.Inject


class StringProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(id: Int): String = context.getString(id)
}