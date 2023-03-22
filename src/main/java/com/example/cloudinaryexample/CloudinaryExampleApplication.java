package com.example.cloudinaryexample;

import com.cloudinary.Cloudinary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudinaryExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudinaryExampleApplication.class, args);
    }


    @Bean
    public Cloudinary cloudinaryBean() {
        return new Cloudinary();
    }
}
