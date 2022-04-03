package ru.netology.exceptions

class PostNotFoundException(postId: Int) : Exception("Пост с таким id не найден (postId=$postId)") {

    }