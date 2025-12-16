package com.turingalan.examen.data.remote

import com.turingalan.examen.data.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookApi {

    @Headers("Accept: application/json")
    @GET("search.json")
    suspend fun searchBooks(@Query("q") query: String): BookSearchResponse
}

