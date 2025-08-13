package com.mateus.encurta_link.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.mateus.encurta_link.models.ShortLink;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShortLinkRepositoryUnitTests {
    
    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Test
    @DisplayName("Test 1: Save Shortlink test")
    @Order(1)
    @Rollback(value = false)
    public void saveShortLinkTest() {
        ShortLink shortLink = new ShortLink();
        shortLink.setExpirationTime(LocalDateTime.now().plusDays(7));
        shortLink.setOriginalLink("https://www.google.com/");
        shortLink.setShortLink("google");
        shortLink.setUser(null);

        shortLinkRepository.save(shortLink);
        
        System.out.println(shortLink);
        Assertions.assertThat(shortLink.getId()).isNotBlank();
    }

    @Test
    @DisplayName("Test 2: Get Shortlink by shortcode test")
    @Order(2)
    @Rollback(value = false)
    public void getShortLinkByShortCode() {
        ShortLink shortLink = shortLinkRepository.findByShortLink("google").get();
        System.out.println(shortLink);

        Assertions.assertThat(shortLink.getId()).isNotBlank();
    }
}
