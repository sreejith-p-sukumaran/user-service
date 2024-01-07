package com.example.user.service;

import com.example.user.model.Post;
import com.example.user.model.User;
import com.example.user.model.UserWithPosts;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUserById(Long userId);
    Mono<Post[]> getPostsByUserId(Long userId);
    Mono<UserWithPosts> getUserWithPosts(Long userId);
}
