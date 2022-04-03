package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.exceptions.CommentNotFoundException
import ru.netology.exceptions.PostNotFoundException
import ru.netology.exceptions.ReportReasonException
import ru.netology.objects.*
import ru.netology.objects.attachments.*

import java.util.*

class PostKtTest {
    @Before
    fun resetWallService() {
        WallService.resetWallService()
    }

    @Test
    fun wallServiceAddPostIdNonZero() {
        val unexpectedPostId = 0
        val expectedPostId = 1
        val post1 = WallService.add(Post())
        val post2 = WallService.add(
            Post(
                id = 0,
                text = "Пост 2",
                attachment = arrayOf(
                    NoteAttachment(
                        note = Note(
                            1, 1, "first note", "it's my 1st note",
                            Date().time
                        )
                    ),
                    PhotoAttachment(photo = Photo(1, 1, 2, "description of photo", 640, 480)),
                    GraffitiAttachment(graffiti = Graffiti(1, 1, "graffiti preview", "full image"))
                )
            )
        )
        val post3 = WallService.add(
            Post(
                id = 9999,
                ownerId = 888,
                fromId = 888,
                createdBy = 888,
                date = Date().time / 1000,
                text = "Пост 3",
                replyOwnerId = 1888,
                replyPostId = -1,
                friendsOnly = true,
                comments = Comments(1, true, true, true, true),
                copyright = Copyright(888, "link", "name", "type"),
                likes = Likes(12, true, true, false),
                reposts = Reposts(3, true),
                views = Views(458),
                postType = "copy",
                postSource = PostSource(type = "widget", platform = "android", data = "poll", url = "vk.com"),
                attachment = arrayOf(
                    AudioAttachment(
                        audio = Audio(
                            1, 1, "name", "title",
                            186, "url of song"
                        )
                    ),
                    VideoAttachment(video = Video(1, 1, "title", "description of video", 3600))
                ),
                //attachment = attachment[0] as VideoAttachment
                geo = Geo(
                    type = "населенный пункт", coordinates = "координаты", Place(
                        id = 1, title = "город",
                        latitude = 1, longitude = 0, created = (Date().time / 1000) - 10000, icon = "url", checkins = 1,
                        updated = Date().time / 1000, type = 1, country = 7, city = 3412, address = "street, house"
                    )
                ),
                signerId = 1888,
                copyHistory = null,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = true,
                isFavorite = true,
                donut = Donut(true, 36000, placeholder = Placeholder(), true, "all"),
                postponedId = 0
            )
        )
        assertEquals(expectedPostId, post1.id)
        assertNotEquals(unexpectedPostId, post2.id)
        assertTrue(post3.id != 0)
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(id = 10001, text = "Текст первого поста"))
        service.add(Post(id = 11001, text = "Текст второго поста"))
        service.add(Post(id = 11001, text = "Текст третьего поста", canPin = true))
        // создаём информацию об обновлении
        Thread.sleep(1000) //для того что бы получить разные значения date для post и updatePost
        val updatePost1 = Post(id = 1, text = "Измененный текст первого поста")
        val updatePost2 = Post(
            id = 2,
            ownerId = 888,
            fromId = 888,
            createdBy = 888,
            date = Date().time / 1000,
            text = "Измененный текст второго поста",
            replyOwnerId = 1888,
            replyPostId = -1,
            friendsOnly = true,
            comments = Comments(1, true, true, true, true),
            copyright = Copyright(888, "link", "name", "type"),
            likes = Likes(12, true, true, false),
            reposts = Reposts(1, false),
            views = Views(458),
            postType = "copy",
            signerId = 1888,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = true,
            isFavorite = true,
            donut = Donut(
                true, 36000, placeholder = Placeholder(),
                true, "all"
            ),
            postponedId = 0
        )
        val updatePost3 = Post(
            id = 3,
            ownerId = 777,
            text = "Измененный текст третьего поста",
            canPin = false,
            canDelete = false,
            isPinned = true
        )
        val result1 = service.update(updatePost1)
        val result2 = service.update(updatePost2)
        val result3 = service.update(updatePost3)

        assertTrue(result1)
        assertTrue(result2)
        assertTrue(result3)
    }

    @Test
    fun updateNonExisting() {
        val service = WallService
        service.add(Post(id = 10001, text = "Текст первого поста"))
        service.add(Post(id = 11001, text = "Текст второго поста"))
        service.add(Post(id = 11001, text = "Текст третьего поста", canPin = true))
        val updatePost = Post(id = 4, text = "Измененный текст второго поста")
        val result = service.update(updatePost)
        assertFalse(result)
    }

    @Test
    fun shouldNotThrow() {
        val post1 = WallService.add(Post())
        val post2 = WallService.add(Post())
        val post3 = WallService.add(Post())
        val post4 = WallService.add(Post())
        val postId = 3
        val idArray = arrayOf(post1.id, post2.id, post3.id, post4.id)
        val comment1 = WallService.createComment(
            1, postId, null, "TEXT", null,
            attachments = arrayOf(
                VideoAttachment(video = Video(id = 1, duration = 36, ownerId = 1)),
                NoteAttachment(note = Note(id = 1, ownerId = 1, text = "Текст", date = 1))
            ), 1, null
        )
        assertTrue(idArray.contains(postId))
        println(comment1.id)
        assertTrue(comment1.id == 1)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post1 = WallService.add(Post())
        val post2 = WallService.add(Post())
        val post3 = WallService.add(Post())
        val post4 = WallService.add(Post())
        val postId = 5
        val idArray = arrayOf(post1.id, post2.id, post3.id, post4.id)
        val comment1 = WallService.createComment(
            1, postId, null, "TEXT", null,
            null, 1, null
        )
        assertFalse(idArray.contains(postId))
        assertFalse(comment1.id == 1)
    }

    @Test
    fun addReportOnComment() {
        WallService.add(Post())
        WallService.add(Post())
        WallService.add(Post())
        WallService.userId = 111 //устанавливаем пользователя, который будет оставлять нехорошие комментарии
        WallService.createComment(0, 1, null, "Нежелательный комментарий от 111 к 1 посту", null, null, null, null)
        WallService.createComment(
            0,
            2,
            null,
            "Еще один нежелательный комментарий от 111 к 2 посту",
            null,
            null,
            null,
            null
        )
        WallService.userId = 222 //устанавливаем второго пользователя, который будет оставлять нехорошие комментарии
        WallService.createComment(0, 2, null, "Нехороший комментарий от 222 ко 2 посту", null, null, null, null)
        WallService.createComment(
            0,
            3,
            null,
            "Второй нежелательный комментарий от 222 к 3 посту",
            null,
            null,
            null,
            null
        )
        WallService.createComment(
            0,
            1,
            null,
            "Третий нежелательный комментарий от 222 к 1 посту",
            null,
            null,
            null,
            null
        )
        val reportOnCommentN1By111 = WallService.reportComment(111, 1, 0)
        val reportOnCommentN2By111 = WallService.reportComment(111, 2, 1)
        val reportOnCommentN3By222 = WallService.reportComment(222, 3, 6)
        val reportOnCommentN4By222 = WallService.reportComment(222, 4, 7)
        val reportOnCommentN5By222 = WallService.reportComment(222, 5, 8)
        assertTrue(reportOnCommentN1By111)
        assertTrue(reportOnCommentN2By111)
        assertTrue(reportOnCommentN3By222)
        assertTrue(reportOnCommentN4By222)
        assertTrue(reportOnCommentN5By222)
    }

    @Test(expected = ReportReasonException::class)
    fun addReportOnCommentWithReportReasonExceptionReasonBelow0() {
        WallService.add(Post())
        WallService.userId = 111 //устанавливаем пользователя, который будет оставлять нехорошие комментарии
        WallService.createComment(0, 1, 1, "Нежелательный комментарий от 111 к 1 посту", 1, null, null, null)
        WallService.reportComment(111, 1, -1)
    }

    @Test(expected = ReportReasonException::class)
    fun addReportOnCommentWithReportReasonExceptionReason9() {
        WallService.add(Post())
        WallService.userId = 111 //устанавливаем пользователя, который будет оставлять нехорошие комментарии
        WallService.createComment(0, 1, null, "Нежелательный комментарий от 111 к 1 посту", null, null, null, null)
        WallService.reportComment(111, 1, 9)
    }

    @Test(expected = CommentNotFoundException::class)
    fun addReportOnCommentWithCommentNotFoundExceptionWrongCommentId() {
        WallService.add(Post())
        WallService.userId = 111 //устанавливаем пользователя, который будет оставлять нехорошие комментарии
        WallService.createComment(0, 1, null, "Нежелательный комментарий от 111 к 1 посту", null, null, null, null)
        WallService.reportComment(111, 0, 5)
    }

    @Test(expected = CommentNotFoundException::class)
    fun addReportOnCommentWithCommentNotFoundExceptionWrongOwnerId() {
        WallService.add(Post())
        WallService.userId = 111 //устанавливаем пользователя, который будет оставлять нехорошие комментарии
        WallService.createComment(0, 1, null, "Нежелательный комментарий от 111 к 1 посту", null, null, null, null)
        WallService.reportComment(555, 1, 4)
    }


}
