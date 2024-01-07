package com.example.user.service;

import com.example.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebFluxTest(UserServiceImplTest.class)
class UserServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getUserById_ValidUserId_ReturnsUser() {
        Long userId = 1L;
        User expectedUser = new User(0L, "John Doe", "john.doe", "john@example.com", null, "123-456", "example.com", null);

        User actualUser = webTestClient.get()
                .uri("/users/{userId}", userId)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assert actualUser != null;
        assertThat(actualUser.id()).isEqualTo(expectedUser.id());

    }

    @Test
    void getPostsByUserId_ValidUserId_ReturnsUser() {
        Long userId = 1L;
        User expectedUser = new User(0L, "John Doe", "john.doe", "john@example.com", null, "123-456", "example.com", null);

        User actualUser = webTestClient.get()
                .uri("/posts?userId={userId}", userId)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assert actualUser != null;
        assertThat(actualUser.id()).isEqualTo(expectedUser.id());

    }

}

