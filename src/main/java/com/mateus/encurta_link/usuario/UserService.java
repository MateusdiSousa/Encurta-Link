package com.mateus.encurta_link.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mateus.encurta_link.exceptions.UserNotFoundException;
import com.mateus.encurta_link.shortLink.type.ShortLinkDtoResponse;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não existe");
        }
        return user.get();
    }

    @Cacheable(value = "userLinks", key = "#email")
    public List<ShortLinkDtoResponse> getUserLinks(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

        return user.getUserLinks().stream().map((link) -> ShortLinkDtoResponse.fromEntity(link)).toList();
    }
}
