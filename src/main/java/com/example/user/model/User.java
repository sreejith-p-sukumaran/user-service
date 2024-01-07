package com.example.user.model;

public record User (long id,
                   String name,
                   String username,
                   String email,
                   Address address,
                   String phone,
                   String website,
                   Company company) {
}
