package com.iticket.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.NetworkInterface;

@SpringBootApplication
public class EventCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventCatalogApplication.class, args);
    }
}
