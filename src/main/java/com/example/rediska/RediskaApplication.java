package com.example.rediska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RediskaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RediskaApplication.class, args);
    }

}
