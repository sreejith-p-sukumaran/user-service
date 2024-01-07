package com.example.user.model;

public record UserWithPosts (User user,
                             Post[] posts) {
}
