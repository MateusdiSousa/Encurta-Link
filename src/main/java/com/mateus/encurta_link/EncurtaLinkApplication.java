package com.mateus.encurta_link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EncurtaLinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncurtaLinkApplication.class, args);
	}

}
