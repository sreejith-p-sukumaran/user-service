package com.example.user.controller;

import com.example.user.model.Post;
import com.example.user.model.User;
import com.example.user.model.UserWithPosts;
import com.example.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebFluxTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    void getUserWithPosts_ShouldReturnUserWithPosts() {
        long userId = 1L;

        UserWithPosts expectedUserWithPosts = new UserWithPosts(
                new User(1, "John Doe", "john.doe", "john@example.com", null, "123-456", "example.com", null),
                new Post[]{new Post(1L, 1L, "Test Title", "Test Body")}
        );

        Mockito.when(userService.getUserWithPosts(userId)).thenReturn(Mono.just(expectedUserWithPosts));

        UserWithPosts actualUserWithPosts = webTestClient.get()
                .uri("/api/users/{userId}", userId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserWithPosts.class)
                .returnResult()
                .getResponseBody();

        Mockito.verify(userService).getUserWithPosts(userId);

        assert actualUserWithPosts != null;
        assertThat(actualUserWithPosts.user().id()).isEqualTo(expectedUserWithPosts.user().id());
        assertThat(actualUserWithPosts.user().address()).isEqualTo(expectedUserWithPosts.user().address());
        assertThat(actualUserWithPosts.user().company()).isEqualTo(expectedUserWithPosts.user().company());

    }
}



