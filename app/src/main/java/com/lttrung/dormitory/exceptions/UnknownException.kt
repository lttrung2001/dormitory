package com.lttrung.dormitory.exceptions

class UnknownException(override val message: String = "Unknown exception") : Exception(message)