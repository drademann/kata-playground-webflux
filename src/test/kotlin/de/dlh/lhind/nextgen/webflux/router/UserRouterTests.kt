package de.dlh.lhind.nextgen.webflux.router

import com.ninjasquad.springmockk.MockkBean
import de.dlh.lhind.nextgen.webflux.model.User
import de.dlh.lhind.nextgen.webflux.service.UserService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono.just
import java.util.*

@AutoConfigureWebTestClient
@SpringBootTest
class UserRouterTests {

    @Autowired
    lateinit var client: WebTestClient

    @MockkBean
    lateinit var mockedUserService: UserService

    @Test
    fun `GET should provide all users`() {
        val expectedAxelID = UUID.randomUUID().toString()
        val expectedHolgerID = UUID.randomUUID().toString()

        val axel = User(expectedAxelID, "Axel")
        val holger = User(expectedHolgerID, "Holger")
        every { mockedUserService.all() } returns Flux.fromIterable(listOf(axel, holger))

        client.get().uri("/users")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(expectedAxelID)
                .jsonPath("$[0].name").isEqualTo("Axel")
                .jsonPath("$[1].id").isEqualTo(expectedHolgerID)
                .jsonPath("$[1].name").isEqualTo("Holger")
    }

    @Test
    fun `POST should accept a new user`() {
        every { mockedUserService.add(any()) } returns just(User("abc", "Oskar Tonne"))

        client.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .bodyValue(
                    """
                {
                    "id":   "abc",
                    "name": "Max Mustermann"
                }
                """
                )
                .exchange()
                .expectStatus().isCreated
                .expectHeader().valueEquals("Location", "/users/abc")
    }

}