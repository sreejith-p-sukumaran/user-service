package com.example.user.model;

public record Post(Long id,
                   Long userId,
                   String title,
                   String body) {

}
