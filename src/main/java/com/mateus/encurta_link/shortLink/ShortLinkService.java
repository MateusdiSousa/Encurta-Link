package com.mateus.encurta_link.shortLink;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mateus.encurta_link.exceptions.ShortLinkConflictException;
import com.mateus.encurta_link.exceptions.ShortLinkNotFoundException;

@Service
public class ShortLinkService {
    private Map<String, String> links;

    public ShortLinkService() {
        this.links = new HashMap<String, String>();
    }

    public String GetLink(String code) throws ShortLinkNotFoundException {
        String link = this.links.get(code);
        if (link == null) {
            throw new ShortLinkNotFoundException("Link não encontrado ou não existe");
        }

        return link;
    }

    public String AddLink(String link, String codigo) throws ShortLinkConflictException {
        // Verify if the already exist a link with this shortcode
        if (codigo == null || codigo.isBlank()) {
            codigo = addLinkWithOutCode(link);
            return codigo;
        }

        if (links.get(codigo) == null) {
            links.put(codigo, link);
            return codigo;
        } else {
            throw new ShortLinkConflictException();
        }
    }

    private String addLinkWithOutCode(String link) {
        while (true) {
            String code = String.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() / 1000);
            // Verify if the already exist a link with this shortcode;
            if (links.get(code) == null) {
                links.put(code, link);
                return code;
            }
        }
    }

}
