package com.global.commtech.anagramfinder.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.global.commtech.anagramfinder")
public class AnagramFinderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnagramFinderApplication.class, args);
    }
}
