package ru.netology.commentObjects

import ru.netology.objects.Placeholder

data class Donut(
    val isDonut: Boolean = false,
    val placeholder: Placeholder = Placeholder(),
)