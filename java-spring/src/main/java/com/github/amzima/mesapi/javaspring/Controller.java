package com.github.amzima.mesapi.javaspring;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class Controller {
    private final TelegramService service;

    public Controller(TelegramService service) {
        this.service = service;
    }

    @GetMapping("/aa")
    public Mono<String> send() {
        return Mono.just("qwe");
    }

    @GetMapping("/mes")
    public Mono<Void> sendMessage(
            @RequestParam(name = "chat_id", required = false) String chatId,
            @RequestParam(required = false) String text
    ) {
        return service.sendMessage(chatId, text);
    }

    @RequestMapping(path = "/doc", method = {RequestMethod.GET, RequestMethod.POST})
    public Mono<Void> sendDocument(
            @RequestParam(name = "chat_id", required = false) String chatId,
            @RequestParam(required = false) String caption,
            @RequestPart Flux<FilePart> document
    ) {
        return service.sendDocument(chatId, caption, document);
    }
}
