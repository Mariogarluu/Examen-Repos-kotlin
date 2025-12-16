package com.turingalan.examen.data.remote

import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.model.BookSearchResponse
import javax.inject.Inject

class BookRemoteDataSourceImpl @Inject constructor(
    private val api: BookApi
) : BookDataSource {

    override suspend fun searchBooks(query: String): BookSearchResponse {
        return api.searchBooks(query)
    }
}