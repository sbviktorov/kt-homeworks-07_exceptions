package ru.netology

import ru.netology.commentObjects.Donut
import ru.netology.objects.Attachment
import java.util.*

data class Comment(
    val id: Int,
    val fromId: Int,
    val date: Long = Date().time,
    val text: String,
    val donut: Donut,
    val replyToUser: Int?,
    val replyToComment: Int? = null,
    val attachment: Array<Attachment>? = null,
    val stickerId: Int?,
    val guid: String?
)
