package com.lttrung.dormitory.utils

class ValidationHelper {
    var hasError = false

    fun isBlank(text: String): Boolean {
        return if (text.isEmpty() or text.isBlank()) {
            hasError = true
            true
        } else {
            false
        }
    }

    fun isPasswordMatched(password: String, confirmPassword: String): Boolean {
        return if (password != confirmPassword) {
            hasError = true
            false
        } else {
            true
        }
    }
}