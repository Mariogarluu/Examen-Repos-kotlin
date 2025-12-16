package com.turingalan.examen.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.turingalan.examen.data.model.Book


//Crea la tabla books
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: String,
    val publicationYear: Int
)

fun BookEntity.toDomain(): Book {
    return Book(
        id = this.id,
        title = this.title,
        authors = if (this.authors.isBlank()) emptyList() else this.authors.split("|"),
        publicationYear = this.publicationYear
    )
}

//Mete los datos el authors se guarda como string pero separado por |
fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = this.id,
        title = this.title,
        authors = this.authors.joinToString("|"),
        publicationYear = this.publicationYear
    )
}