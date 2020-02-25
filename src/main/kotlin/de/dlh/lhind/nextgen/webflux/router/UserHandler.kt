package de.dlh.lhind.nextgen.webflux.router

import de.dlh.lhind.nextgen.webflux.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body

@Component
class UserHandler(val userService: UserService) {

    fun get(serverRequest: ServerRequest) = ServerResponse.ok().body(userService.all())

}