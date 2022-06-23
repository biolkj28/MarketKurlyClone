package com.nineteam.marketkurlycloneproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarketKurlyCloneProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketKurlyCloneProjectApplication.class, args);
    }

}
