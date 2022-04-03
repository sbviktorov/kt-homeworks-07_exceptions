package ru.netology.objects

data class PostSource (
    val type: String = "vk",
    val platform: String? = null,
    val data: String? = null,
    val url: String? = null
)