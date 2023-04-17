package com.lttrung.dormitory.exceptions

class NotFoundException(override val message: String = "Not found exception") : Exception(message)