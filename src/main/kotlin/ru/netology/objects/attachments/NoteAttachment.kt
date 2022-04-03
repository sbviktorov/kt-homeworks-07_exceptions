package ru.netology.objects.attachments

import ru.netology.objects.Attachment

data class NoteAttachment(override val type: String = "note", val note: Note) : Attachment {
}

data class Note(
    val id: Int,
    val ownerId: Int,
    var title: String? = null,
    val text: String,
    val date: Long
)