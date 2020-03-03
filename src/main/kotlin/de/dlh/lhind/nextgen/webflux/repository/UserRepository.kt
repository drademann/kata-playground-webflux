package de.dlh.lhind.nextgen.webflux.repository

import de.dlh.lhind.nextgen.webflux.model.User
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import kotlin.collections.HashMap

@Repository
class UserRepository {

    private val users = HashMap<String, User>()

    fun all(): Flux<User> = Flux.fromIterable(users.values)
    fun by(id: String): Mono<User> = Mono.justOrEmpty(users[id])

    fun save(user: User): Mono<User> {
        val newUser = User(UUID.randomUUID().toString(), user.name)
        users[user.id] = newUser
        return by(newUser.id)
    }

    fun delete(user: User): Mono<User> = Mono.justOrEmpty(users.remove(user.id))

}