package ru.netology.objects.attachments

import ru.netology.objects.Attachment

data class AudioAttachment(override val type: String = "audio", val audio: Audio) : Attachment {
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String
)