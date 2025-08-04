package com.mateus.encurta_link.shortLink.type;

import com.mateus.encurta_link.shortLink.ShortLink;

public record ShortLinkDtoResponse(String id, String originalLink, String shortLink, String userEmail) {
    public static ShortLinkDtoResponse fromEntity(ShortLink link) {
        return new ShortLinkDtoResponse(
                link.getId(),
                link.getOriginalLink(),
                link.getShortLink(),
                link.getUser());
    }
}
