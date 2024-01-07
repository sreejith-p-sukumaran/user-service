package com.example.user.model;

public record Address(String street,
                      String suite,
                      String city,
                      String zipcode,
                      Geo geo) {
}
