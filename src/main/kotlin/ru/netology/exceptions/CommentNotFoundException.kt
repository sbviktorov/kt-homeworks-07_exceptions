package ru.netology.exceptions

class CommentNotFoundException(commentId: Int, ownerId: Int): Exception("Не найден комментарий с commentId $commentId и ownerId $ownerId.") {
}