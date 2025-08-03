package com.mateus.encurta_link.shortLink;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortLinkRepository extends JpaRepository<ShortLink, String> {
    Optional<ShortLink> findByShortLink(String shortLink);
}