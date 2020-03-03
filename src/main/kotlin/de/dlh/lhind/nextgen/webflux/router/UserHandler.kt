package de.dlh.lhind.nextgen.webflux.router

import de.dlh.lhind.nextgen.webflux.model.User
import de.dlh.lhind.nextgen.webflux.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.net.URI

@Component
class UserHandler(val userService: UserService) {

    fun get(serverRequest: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(userService.all())

    fun post(serverRequest: ServerRequest): Mono<ServerResponse> =
        serverRequest.bodyToMono(User::class.java)
            .flatMap { user -> userService.add(user) }
            .flatMap { newUser ->
                ServerResponse
                    .created(URI.create("/users/${newUser.id}"))
                    .bodyValue(newUser)
            }

}