package com.turingalan.examen.data.remote

import com.turingalan.examen.data.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookApi {

    //Montar la consulta a la API
    @Headers("Accept: application/json")
    @GET("/search.json")
    //Se manda la query
    suspend fun searchBooks(@Query("q") query: String): BookSearchResponse
}

