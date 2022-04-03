package ru.netology.objects

data class Place (
    val id: Int,
    val title: String,
    val latitude: Int,
    val longitude: Int,
    val created: Long,
    val icon: String,
    val checkins: Int,
    val updated: Long,
    val type: Int,
    val country: Int,
    val city: Int,
    val address: String
)