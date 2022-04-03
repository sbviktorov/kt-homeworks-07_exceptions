package ru.netology

import ru.netology.exceptions.ReportReasonException

data class ReportsOnComments(
    val reportsOnCommentsId: Int,
    val ownerId: Int,
    val commentId: Int,
    val reason: Int
)
{
    init {
        if (reason !in 0..8) {
            throw ReportReasonException(reason)
        }
    }

}
