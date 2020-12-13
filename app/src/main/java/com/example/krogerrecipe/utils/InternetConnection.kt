package com.example.krogerrecipe.utils

import android.content.Context
import android.net.ConnectivityManager

object InternetConnection {
    fun checkConnection(context: Context): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
    }
}
