package com.mateus.encurta_link.shortLink;

import java.time.LocalDateTime;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mateus.encurta_link.exceptions.ShortLinkConflictException;
import com.mateus.encurta_link.exceptions.ShortLinkNotFoundException;
import com.mateus.encurta_link.exceptions.UserNotFoundException;
import com.mateus.encurta_link.shortLink.type.ShortLinkDtoRequest;
import com.mateus.encurta_link.user.User;
import com.mateus.encurta_link.user.UserRepository;
import com.mateus.encurta_link.utils.RandomAlphanumeric;

import jakarta.transaction.Transactional;

@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;
    private final UserRepository userRepository;

    public ShortLinkService(ShortLinkRepository shortLinkRepository, UserRepository userRepository) {
        this.shortLinkRepository = shortLinkRepository;
        this.userRepository = userRepository;
    }

    @Cacheable(value = "shorLink", key = "#code")
    public String GetLink(String code) throws ShortLinkNotFoundException {
        ShortLink link = this.shortLinkRepository.findByShortLink(code)
                .orElseThrow(() -> new ShortLinkNotFoundException());
        return link.getOriginalLink();
    }

    public ShortLink AddLink(ShortLinkDtoRequest dto, String email)
            throws ShortLinkConflictException, UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

        if (dto.shortLink() == null) {
            String code = RandomShortLink();
            return saveLink(dto.link(), code, user);
        }

        boolean linkExist = shortLinkRepository.findByShortLink(dto.shortLink()).isPresent();
        if (linkExist) {
            throw new ShortLinkConflictException();
        }

        return saveLink(dto.link(), dto.shortLink(), user);
    }

    private ShortLink saveLink(String link, String shortLink, User user) {
        ShortLink newShortLink = new ShortLink();
        newShortLink.setUser(user);
        newShortLink.setOriginalLink(link);
        newShortLink.setShortLink(shortLink);
        newShortLink.setExpirationTime(LocalDateTime.now().plusDays(30));
        return shortLinkRepository.save(newShortLink);
    }

    private String RandomShortLink() {
        while (true) {
            String code = RandomAlphanumeric.GenerateString();
            if (shortLinkRepository.findByShortLink(code).isEmpty()) {
                return code;
            }
        }
    }

    @Transactional
    public void removeExpiredLinks() {
        shortLinkRepository.deleteByExpirationTimeBefore(LocalDateTime.now());

        System.out.println("\n\n\n\nShort Links removed\n\n\n\n\n");
    }
}
