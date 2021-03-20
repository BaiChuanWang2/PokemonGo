package com.example.pokemongo

import android.content.Context

object AppHelper {
    lateinit var appContext: Context

    fun initAppContext(context: Context) {
        appContext = context
    }
}