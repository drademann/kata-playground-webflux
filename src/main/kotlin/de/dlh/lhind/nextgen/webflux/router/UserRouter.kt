package de.dlh.lhind.nextgen.webflux.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRouter(private val handler: UserHandler) {

    @Bean
    fun routes() = router {
        GET("/users", handler::get)
        POST("/users", handler::post)
    }

}