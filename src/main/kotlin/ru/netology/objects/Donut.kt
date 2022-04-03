package ru.netology.objects

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val placeholder: Placeholder = Placeholder(),
    val canPublishFreeCopy: Boolean = true,
    val editMode: String = "duration"
)
