package de.dlh.lhind.nextgen.webflux.repository

import de.dlh.lhind.nextgen.webflux.model.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import kotlin.collections.HashMap

@Repository
class UserRepository {

    private val users = HashMap<UUID, User>()

    fun all(): Flux<User> = Flux.fromIterable(users.values)
    fun by(id: UUID): Mono<User> = Mono.justOrEmpty(users[id])

    fun save(user: User) {
        users[user.id] = user
    }

    fun delete(user: User) {
        users.remove(user.id)
    }

}