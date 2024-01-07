package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.model.UserWithPosts;
import com.example.user.service.UserService;
import com.example.user.service.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "getUserWithPostsBreaker", fallbackMethod = "getUserWithPostsFallback")
    public Mono<UserWithPosts> getUserWithPosts(@PathVariable
                                                @NotNull
                                                @Positive(message = "userId must be a positive number")
                                                Long userId) {
        logger.info("User information with posts retrieved");
        return userService.getUserWithPosts(userId);
    }

    private Mono<User> getUserWithPostsFallback(Long userId, Throwable throwable) {
        logger.error("Circuit breaker triggered for getUserById with userId: " + userId);
        return Mono.just(new User(1, "Default User", "", "", null, "", "", null));
    }

}
