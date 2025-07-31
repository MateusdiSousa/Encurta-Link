package com.mateus.encurta_link.shortLink;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mateus.encurta_link.exceptions.ShortLinkNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("link")
public class ShortLinkController {
    @Autowired
    private ShortLinkService encurtadorService;


    @GetMapping("{codigo}")
    public RedirectView RedirecionarLink(@PathVariable(name = "codigo") String codigo) throws ShortLinkNotFoundException {
        String link = this.encurtadorService.GetLink(codigo);
        return new RedirectView(link);
    }

    @PostMapping("")
    public ResponseEntity<String> postMethodName(@RequestBody ShortLinkDto entity) {
        String code = this.encurtadorService.AddLink(entity.link(), entity.shortLink());
     
        return ResponseEntity.ok("Short link created with code '" + code + "'.");
    }
    
    
    
}
