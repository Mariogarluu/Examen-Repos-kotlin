package com.turingalan.examen.data

import com.turingalan.examen.data.model.BookSearchResponse

interface BookDataSource {
    suspend fun searchBooks(query: String): BookSearchResponse
}