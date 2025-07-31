package com.mateus.encurta_link.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.encurta_link.authentication.types.AuthenticationResponse;
import com.mateus.encurta_link.authentication.types.UserRegisterRequest;
import com.mateus.encurta_link.exceptions.InvalidCredentialsException;
import com.mateus.encurta_link.exceptions.UserAlreadyExistException;
import com.mateus.encurta_link.usuario.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign")
    public AuthenticationResponse RegisterUser(@RequestBody UserRegisterRequest request)
            throws UserAlreadyExistException {
        AuthenticationResponse response = authenticationService.Register(request);

        return response;
    }

    @PostMapping("/login")
    public AuthenticationResponse Login(@RequestBody User request) throws InvalidCredentialsException {
        AuthenticationResponse response = authenticationService.Authenticate(request);

        return response;
    }

}
