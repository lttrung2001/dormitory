package com.lttrung.dormitory.domain.data.local.models

data class FilterSortModel(
    val minBeds: Int,
    val maxBeds: Int,
    val bedsRange: List<Int>,
    val sortPosition: Int
) : java.io.Serializable