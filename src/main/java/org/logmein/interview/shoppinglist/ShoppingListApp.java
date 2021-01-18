package org.logmein.interview.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShoppingListApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingListApp.class, args);
    }
}
