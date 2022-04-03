package ru.netology.exceptions

class ReportReasonException(reason: Int) : Exception("Некорректное значение причины жалобы ($reason), должно быть от 0 до 8."){
}