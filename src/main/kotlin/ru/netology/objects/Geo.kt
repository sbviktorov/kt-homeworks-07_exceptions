package ru.netology.objects

data class Geo(
    val type: String = "",
    val coordinates: String = "",
    var place: Place? = null
)