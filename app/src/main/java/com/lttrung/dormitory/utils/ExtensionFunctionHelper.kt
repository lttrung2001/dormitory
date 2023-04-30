package com.lttrung.dormitory.utils

import java.text.SimpleDateFormat
import java.util.*

object ExtensionFunctionHelper {
    fun Date.format(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return formatter.format(this)
    }
}