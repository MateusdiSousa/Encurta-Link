package com.mateus.encurta_link.shortLink;

import java.time.LocalDateTime;

import com.mateus.encurta_link.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "link")
@AllArgsConstructor
@NoArgsConstructor
public class ShortLink {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @Column
    private String originalLink;

    @Column(unique = true)
    private String shortLink;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public String getUser() {
        return user.getEmail();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpirationTime(LocalDateTime date) {
        this.expirationTime = date;
    }

    public LocalDateTime getExpirationTime() {
        return this.expirationTime;
    }

}