package com.mateus.encurta_link.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.encurta_link.authentication.types.AuthenticationResponse;
import com.mateus.encurta_link.authentication.types.UserRegisterRequest;
import com.mateus.encurta_link.documentation.IAuthenticationController;
import com.mateus.encurta_link.exceptions.InvalidCredentialsException;
import com.mateus.encurta_link.exceptions.UserAlreadyExistException;
import com.mateus.encurta_link.user.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("auth")
public class AuthenticationController implements IAuthenticationController{
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserRegisterRequest request)
            throws UserAlreadyExistException {
        AuthenticationResponse response = authenticationService.Register(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) throws InvalidCredentialsException {
        AuthenticationResponse response = authenticationService.Authenticate(request);

        return ResponseEntity.ok(response);
    }
}
