package com.lttrung.dormitory.utils

object ValidationHelper {
    var hasError = false
    var numberOfErrors = 0

    fun isBlank(text: String): Boolean {
        return if (text.isEmpty() or text.isBlank()) {
            hasError = true
            numberOfErrors++
            true
        } else {
            false
        }
    }

    fun isPasswordMatched(password: String, confirmPassword: String): Boolean {
        return if (password != confirmPassword) {
            hasError = true
            numberOfErrors++
            false
        } else {
            true
        }
    }
}