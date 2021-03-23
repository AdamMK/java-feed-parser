package com.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.ip.tcp.connection.ConnectionFactory;

import javax.net.SocketFactory;
import java.io.*;
import java.net.Socket;

@SpringBootApplication
public class FeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class, args);

        }
}
