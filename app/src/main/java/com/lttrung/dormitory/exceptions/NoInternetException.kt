package com.lttrung.dormitory.exceptions

class NoInternetException(override val message: String = "No internet connection") : Exception(message)