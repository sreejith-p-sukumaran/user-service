package com.example.user.service;

import com.example.user.exception.UserNotFoundException;
import com.example.user.model.Post;
import com.example.user.model.User;
import com.example.user.model.UserWithPosts;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final WebClient webClient;

    public UserServiceImpl(WebClient.Builder webClientBuilder, @Value("${user.service.baseUrl}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<User> getUserById(Long userId) {
        return webClient.get()
                .uri("/users/{userId}", userId)
                .retrieve()
                .onStatus(
                        status -> status == HttpStatus.NOT_FOUND,
                        response -> Mono.empty()
                )
                .bodyToMono(User.class)
                .onErrorResume(
                        WebClientResponseException.class,
                        this::handleUserResponseException
                );
    }

    @Override
    public Mono<Post[]> getPostsByUserId(Long userId) {
        return webClient.get()
                .uri("/posts?userId={userId}", userId)
                .retrieve()
                .onStatus(
                        status -> status == HttpStatus.NOT_FOUND,
                        response -> Mono.empty()
                )
                .bodyToMono(Post[].class)
                .onErrorResume(
                        WebClientResponseException.class,
                        this::handlePostsResponseException
                );
    }

    @Override
    public Mono<UserWithPosts> getUserWithPosts(Long userId) {
        Mono<User> userMono = getUserById(userId);
        Mono<Post[]> postsMono = getPostsByUserId(userId);

        return Mono.zip(userMono, postsMono, UserWithPosts::new)
                .flatMap(userWithPosts -> {
                    User user = userWithPosts.user();
                    Post[] posts = userWithPosts.posts();

                    if (user != null && posts != null && posts.length > 0) {
                        return Mono.just(userWithPosts);
                    } else {
                        return Mono.error(new UserNotFoundException("User or posts is empty"));
                    }
                })
                .onErrorResume(
                        throwable -> handleUserWithPostsResponseException(throwable, userId)
                );
    }

    private Mono<User> handleUserResponseException(WebClientResponseException ex) {
        logger.error("WebClient request failed with status code: " + ex.getStatusCode());
        return Mono.error(ex);
    }

    private Mono<Post[]> handlePostsResponseException(WebClientResponseException ex) {
        logger.error("WebClient request failed with status code: " + ex.getStatusCode());
        return Mono.error(ex);
    }

    private Mono<UserWithPosts> handleUserWithPostsResponseException(Throwable throwable, Long userId) {
        logger.error("Error fetching user with posts for userId: " + userId);
        return Mono.error(new RuntimeException("Error fetching user with posts for userId: " + userId));
    }
}

