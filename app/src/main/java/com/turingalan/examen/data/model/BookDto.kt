package com.turingalan.examen.data.model

import com.google.gson.annotations.SerializedName

data class BookSearchResponse(
    @SerializedName("docs") val docs: List<BookDto>
)

data class BookDto(
    @SerializedName("key") val key: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authorNames: List<String>?,
    @SerializedName("first_publish_year") val firstPublishYear: Int?
)