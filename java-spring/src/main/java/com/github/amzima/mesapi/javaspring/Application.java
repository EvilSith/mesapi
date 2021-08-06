package com.github.amzima.mesapi.javaspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routes(@Value("classpath:/public/index.html") Resource html) {
        return RouterFunctions.route(
                RequestPredicates.GET("/"),
                req -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .bodyValue(html)
        );
    }
}
