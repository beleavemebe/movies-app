package com.github.beleavemebe.moviesapp.utils

import android.util.Log

const val TAG = "movies-debug"

@Suppress("unused")
fun Any.log(msg: Any?) = Log.d(TAG, msg.toString())
