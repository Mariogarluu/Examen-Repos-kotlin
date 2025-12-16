package com.turingalan.examen.data.repository

import com.turingalan.examen.common.exception.BookNotFoundException
import com.turingalan.examen.data.BookDataSource
import com.turingalan.examen.data.local.BookDao
import com.turingalan.examen.data.local.BookEntity
import com.turingalan.examen.data.local.toDomain
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.remote.getWorkId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val dataSource: BookDataSource,
    private val dao: BookDao
) : BookRepository {

    override suspend fun readOne(id: String): Result<Book> {
        val entity = dao.getBookById(id)
        return if (entity != null) {
            Result.success(entity.toDomain())
        } else {
            Result.failure(BookNotFoundException())
        }
    }

    override suspend fun observeByQuery(search: String): Flow<Result<List<Book>>> = flow {
        try {
            val response = dataSource.searchBooks(search)
            val entities = response.docs.map { dto ->
                BookEntity(
                    id = getWorkId(dto.key),
                    title = dto.title,
                    authors = dto.authorNames?.joinToString("|") ?: "",
                    publicationYear = dto.firstPublishYear ?: 0
                )
            }
            dao.insertAll(entities)

            dao.observeBooksByQuery(search).map { list ->
                Result.success(list.map { it.toDomain() })
            }.collect {
                emit(it)
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}