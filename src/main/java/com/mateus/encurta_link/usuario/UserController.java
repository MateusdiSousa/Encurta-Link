package com.mateus.encurta_link.usuario;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.encurta_link.exceptions.UserNotFoundException;
import com.mateus.encurta_link.security.JwtService;
import com.mateus.encurta_link.shortLink.type.ShortLinkDtoResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/links")
    public ResponseEntity<List<ShortLinkDtoResponse>> GetUserLinks(@RequestHeader(name = "Authorization") String bearerToken)
            throws UserNotFoundException {
        String token = bearerToken.substring("bearer ".length());
        String email = jwtService.extractEmail(token);

        return ResponseEntity.ok(userService.getUserLinks(email));
    }
}
