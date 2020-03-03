package de.dlh.lhind.nextgen.webflux.repository

import de.dlh.lhind.nextgen.webflux.model.Book
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import kotlin.collections.HashMap

@Repository
class BookRepository {

    private val books = HashMap<String, Book>()

    fun all(): Flux<Book> = Flux.fromIterable(books.values)
    fun by(id: String): Mono<Book> = Mono.justOrEmpty(books[id])

    fun save(book: Book): Mono<Book> {
        val newBook = Book(UUID.randomUUID().toString(), book.title)
        books[book.id] = book
        return by(newBook.id)
    }

    fun delete(book: Book): Mono<Book> = Mono.justOrEmpty(books.remove(book.id))

}