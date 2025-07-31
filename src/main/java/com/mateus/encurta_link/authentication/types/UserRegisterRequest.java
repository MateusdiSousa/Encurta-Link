package com.mateus.encurta_link.authentication.types;

public record UserRegisterRequest(
    String email,
    String password
) {
}
