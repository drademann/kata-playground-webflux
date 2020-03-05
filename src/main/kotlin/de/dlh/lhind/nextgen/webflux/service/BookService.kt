package de.dlh.lhind.nextgen.webflux.service

import de.dlh.lhind.nextgen.webflux.model.Book
import de.dlh.lhind.nextgen.webflux.model.User
import de.dlh.lhind.nextgen.webflux.repository.BookRepository
import de.dlh.lhind.nextgen.webflux.repository.UserBookRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userBookRepository: UserBookRepository
) {

    fun booksByUser(user: User): Flux<Book> {
        return userBookRepository.bookIdsByUser(user.id)
                .flatMap { bookId -> bookRepository.by(bookId) }
    }

}