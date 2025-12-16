package com.turingalan.examen.data.repository
import com.turingalan.examen.common.exception.BookNotFoundException
import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject


class BookFakeRepositoryImpl @Inject constructor(
    private val scope: CoroutineScope
): BookRepository {

    private val _fakeBooks: MutableList<Book> = mutableListOf(
        Book(
            id = "OL1812933W",
            title = "American Psycho",
            authors = listOf("Bret Easton Ellis","Mariano Buendia"),
            publicationYear = 1991,

        ),
        Book(
            id = "OL102749W",
            authors = listOf("Herman Melville"),
            title = "Moby Dick",
            publicationYear = 1851,
        ),
        Book(
            id = "OL27513W",
            title = "The Fellowship of the Ring",
            authors = listOf("J.R.R. Tolkien"),
            publicationYear = 1954,
        ),
        Book(
            id = "OL27479W",
            title = "The Two Towers",
            authors =listOf("J.R.R. Tolkien"),
            publicationYear = 1954,
        ),
        Book(
            id = "OL27448W",
            title = "The Return of the King",
            authors = listOf("J.R.R. Tolkien"),
            publicationYear = 1955,
        ),
        Book(
            id = "OL85892W",
            title = "Dracula",
            authors = listOf("Bram Stoker"),
            publicationYear = 1897,
        ),
        Book(
            id = "OL2172356W",
            title = "¿Sueñan los Androides con Ovejas Eléctricas?",
            authors = listOf("Philip K. Dick"),
            publicationYear = 1968,
        ),
        Book(
            id = "OL66562W",
            title = "Sense and Sensibility",
            authors = listOf("Jane Austen"),
            publicationYear = 1811,
        ),
        Book(
            id = "OL278145W",
            title = "The Lady with the Dog",
            authors = listOf("Антон Павлович Чехов"),
            publicationYear = 1899,
        ),
        Book(
            id = "OL3335245W",
            title = "The Catcher in the Rye",
            authors = listOf("J. D. Salinger"),
            publicationYear = 1945,
        ),
    )

    override suspend fun readOne(id: String): Result<Book> {
        val book = _fakeBooks.firstOrNull {book -> book.id == id}
        book?.let {
            return Result.success(book)
        }
        return Result.failure(exception=BookNotFoundException())

    }

    override suspend fun observeByQuery(search: String): Flow<Result<List<Book>>> {

        val filtered = _fakeBooks.filter {
            book ->
                book.title.contains(other = search, ignoreCase = true)
        }
        return flow {
            delay(2_000L)
            emit(Result.success(filtered))
        }.shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000L),
            replay = 1,
        )
    }
}