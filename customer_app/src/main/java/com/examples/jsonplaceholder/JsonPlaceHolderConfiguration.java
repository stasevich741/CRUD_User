package com.examples.jsonplaceholder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonPlaceHolderConfiguration {

    @Bean("jsonplaceholder")
    CommandLineRunner runner(JsonPlaceHolderClient jsonPlaceHolderClient) {
        return args -> {
            System.err.println("https://jsonplaceholder.typicode.com/posts");
            System.err.println(jsonPlaceHolderClient.getPosts().size());

            System.err.println();

            System.err.println("https://jsonplaceholder.typicode.com/posts/1");
            System.err.println(jsonPlaceHolderClient.getPost(1));
        };
    }
}
