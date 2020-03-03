package de.dlh.lhind.nextgen.webflux.service

import de.dlh.lhind.nextgen.webflux.model.User
import de.dlh.lhind.nextgen.webflux.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(val userRepository: UserRepository) {

    fun all(): Flux<User> = userRepository.all()

    fun add(user: User): Mono<User> = userRepository.save(user)
    fun update(id: String, user: User) = userRepository.save(user)
}