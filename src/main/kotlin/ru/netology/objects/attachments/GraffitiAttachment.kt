package ru.netology.objects.attachments

import ru.netology.objects.Attachment

data class GraffitiAttachment(override val type: String = "graffiti", val graffiti: Graffiti) : Attachment {
}

data class Graffiti(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String
)