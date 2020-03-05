package de.dlh.lhind.nextgen.webflux.repository

import de.dlh.lhind.nextgen.webflux.model.UserBook
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class UserBookRepository {

    private val userBooks = HashSet<UserBook>()

    fun add(userId: String, bookId: String) {
        userBooks.add(UserBook(userId, bookId))
    }

    fun bookIdsByUser(userId: String): Flux<String> =
        Flux.fromIterable(userBooks.filter { it.userId == userId }
                                  .map { it.bookId })

}