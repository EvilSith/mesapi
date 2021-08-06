package com.github.amzima.mesapi.javaspring;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class TelegramService {
    private final WebClient client;
    private final String telegramToken;
    private final String defaultMessage;
    private final String defaultChatId;

    public TelegramService(
            @Value("${telegram-api}") String telegramApi,
            @Value("${telegram-token}") String telegramToken,
            @Value("${default-message}") String defaultMessage,
            @Value("${default-chat-id}") String defaultChatId,
            @NotNull WebClient.Builder builder
    ) {
        this.telegramToken = telegramToken;
        this.defaultMessage = defaultMessage;
        this.defaultChatId = defaultChatId;
        this.client = builder.baseUrl(telegramApi).build();
    }

    public Mono<Void> sendMessage(@Nullable String chatId, @Nullable String message) {
        return client.post().uri("/bot" + telegramToken + "/sendMessage")
                .body(BodyInserters.fromFormData("chat_id", Optional.ofNullable(chatId).orElse(defaultChatId))
                        .with("text", Optional.ofNullable(message).orElse(defaultMessage))
                        .with("parse_mode", "markdown"))
                .retrieve()
                .toBodilessEntity()
                .then();
    }

    public Mono<Void> sendDocument(@Nullable String chatId, @Nullable String caption, Flux<FilePart> filePart) {
        return filePart.singleOrEmpty()
                .flatMap(data -> {
                            final var partBuilder = BodyInserters
                                    .fromMultipartData("chat_id", Optional.ofNullable(chatId).orElse(defaultChatId))
                                    .with("parse_mode", "markdown");

                            if (null != caption) {
                                partBuilder.with("caption", caption);
                            }
                            return client.post().uri("/bot" + telegramToken + "/sendDocument")
                                    .contentType(MediaType.MULTIPART_FORM_DATA)
                                    .body(partBuilder.with("document", data))
                                    .retrieve()
                                    .toBodilessEntity()
                                    .then();
                        }
                );
    }
}
