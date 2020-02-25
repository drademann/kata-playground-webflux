package de.dlh.lhind.nextgen.webflux.router

import com.ninjasquad.springmockk.MockkBean
import de.dlh.lhind.nextgen.webflux.model.User
import de.dlh.lhind.nextgen.webflux.service.UserService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import java.util.*

@AutoConfigureWebTestClient
@SpringBootTest
class UserRouterTests {

    @Autowired
    lateinit var client: WebTestClient

    @MockkBean
    lateinit var mockedUserService: UserService

    @Test
    fun `should provide all users`() {
        val expectedAxelID = UUID.randomUUID()
        val expectedHolgerID = UUID.randomUUID()

        val dirk = User(expectedAxelID, "Axel")
        val holger = User(expectedHolgerID, "Holger")
        every { mockedUserService.all() } returns Flux.fromIterable(listOf(dirk, holger))

        client.get().uri("/users")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(expectedAxelID.toString())
                .jsonPath("$[0].name").isEqualTo("Axel")
                .jsonPath("$[1].id").isEqualTo(expectedHolgerID.toString())
                .jsonPath("$[1].name").isEqualTo("Holger")
    }

}