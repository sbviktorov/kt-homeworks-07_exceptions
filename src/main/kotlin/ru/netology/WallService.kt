package ru.netology

import ru.netology.commentObjects.Donut
import ru.netology.exceptions.CommentNotFoundException
import ru.netology.exceptions.PostNotFoundException
import ru.netology.objects.Attachment

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    var reportsOnComments = emptyArray<ReportsOnComments>()
    private var postId: Int = 1
    private var commentId: Int = 1
    private var reportsOnCommentsId: Int = 1
    var userId: Int = 999
    fun add(post: Post): Post {

        val newPost = post.copy(id = postId++)
        posts += newPost
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for (index in posts.indices) {
            if (post.id == posts[index].id) {
                posts[index] = post.copy(
                    ownerId = posts[index].ownerId,
                    date = posts[index].date
                )
                return true
            }
        }
        return false
    }

    fun resetWallService() {
        posts = emptyArray<Post>()
        comments = emptyArray<Comment>()
        reportsOnComments = emptyArray<ReportsOnComments>()
        postId = 1
        commentId = 1
        reportsOnCommentsId = 1
    }

    fun createComment(
        ownerId: Int,
        postId: Int,
        fromGroup: Int?,
        message: String,
        replyToComment: Int?,
        attachments: Array<Attachment>?,
        stickerId: Int?,
        guid: String?
    ): Comment {
        if (isPostExists(postId)) {
            val newComment = Comment(
                id = commentId++,
                text = message,
                donut = Donut(),
                replyToUser = ownerId,
                attachment = attachments,
                stickerId = stickerId,
                guid = guid,
                fromId = userId
            )
            comments += newComment
            return comments.last()
        } else {
            throw PostNotFoundException(postId)
        }
    }

    private fun isPostExists(postId: Int): Boolean {
        for (i in posts.indices) {
            if (posts[i].id == postId) {
                return true
            }
        }
        return false
    }

    fun reportComment(
        ownerId: Int,//id комментатора, а в Comment id комментатора комментария хранится в Comment.fromId
        commentId: Int,
        reason: Int
    ): Boolean {
        for (i in comments.indices) {
            if ((comments[i].id == commentId) && (comments[i].fromId == ownerId)) {
                val newReportOnComment =
                    ReportsOnComments(reportsOnCommentsId = reportsOnCommentsId++, ownerId, commentId, reason)
                reportsOnComments += newReportOnComment
                return true
            }
        }
        throw CommentNotFoundException(commentId, ownerId)
    }
}

