package com.lttrung.dormitory.exceptions

class FailedException(override val message: String = "Failed exception") : Exception(message)